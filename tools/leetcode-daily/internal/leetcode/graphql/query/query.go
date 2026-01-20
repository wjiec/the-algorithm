package query

import (
	"bytes"
	"errors"
	"fmt"
	"iter"
	"reflect"
	"slices"
	"strings"
)

var (
	// ErrNotStruct represents the input object to be reflected is not a struct.
	ErrNotStruct = errors.New("graphql: must be a struct value or pointer to struct")
)

type Querier interface {
	QueryName() string
}

func Encode[T Querier, V any]() ([]byte, error) {
	e := newEncoder()
	if err := e.Encode(*new(T), *new(V)); err != nil {
		return nil, err
	}
	return e.Bytes(), nil
}

type encoder struct {
	out bytes.Buffer
	lvl int
	ind string

	vars variables
}

func newEncoder() *encoder {
	return &encoder{ind: "  "}
}

func (e *encoder) Encode(q Querier, v any) error {
	rt := indirect(reflect.TypeOf(q))
	if rt.Kind() != reflect.Struct {
		return ErrNotStruct
	}

	vars, err := encodeVariables(v)
	if err != nil {
		return err
	}

	e.vars = vars
	return e.nested(e.encodeStruct(rt), "query", q.QueryName(), vars.String())
}

func (e *encoder) Bytes() []byte {
	return e.out.Bytes()
}

func (e *encoder) encodeStruct(rt reflect.Type) func() error {
	return func() error {
		for i := 0; i < rt.NumField(); i++ {
			if ft := rt.Field(i); ft.IsExported() && !ft.Anonymous {
				if prefix, ok := e.parseTag(ft.Tag.Get("graphql")); ok {
					if frt := indirect(ft.Type); frt.Kind() == reflect.Struct {
						if err := e.nested(e.encodeStruct(frt), prefix); err != nil {
							return err
						}
					} else {
						e.out.WriteString(e.indent(prefix + "\n"))
					}
				}
			}
		}
		return nil
	}
}

func (e *encoder) nested(f func() error, prefix ...string) error {
	e.out.WriteString(e.indent(slices.Concat(prefix, []string{"{\n"})...))
	defer func() { e.out.WriteString(e.indent("}\n")) }()
	defer func() { e.lvl-- }()

	e.lvl++
	return f()
}

func (e *encoder) indent(s ...string) string {
	return strings.Repeat(e.ind, e.lvl) + strings.Join(s, " ")
}

func (e *encoder) parseTag(tag string) (string, bool) {
	name, attrs, found := parseTag(tag)
	if !found {
		return "", false
	}

	inline, alias, args := false, "", make([]string, 0)
	for k, v := range attrs {
		switch strings.ToLower(k) {
		case "inline":
			inline = true
		case "alias":
			alias = strings.TrimSpace(v)
		case "arg":
			if argName, argValue, ok := strings.Cut(v, "="); ok {
				args = append(args, fmt.Sprintf("%s: %s", argName, argValue))
			}
		case "var-args":
			for _, elem := range e.vars {
				args = append(args, fmt.Sprintf("%s: %s", elem.Name, elem.WrapName()))
			}
		}
	}

	var prefix string
	if inline {
		prefix = "... on "
	}

	var suffix string
	if len(args) != 0 {
		suffix = "(" + strings.Join(args, ", ") + ")"
	}

	if len(alias) != 0 {
		return prefix + alias + ": " + name + suffix, true
	}
	return prefix + name + suffix, true
}

func indirect(rt reflect.Type) reflect.Type {
	for rt.Kind() == reflect.Pointer || rt.Kind() == reflect.Slice || rt.Kind() == reflect.Array {
		rt = rt.Elem()
	}
	return rt
}

func parseTag(tag string) (string, iter.Seq2[string, string], bool) {
	if len(tag) == 0 {
		return "", nil, false
	}

	name, attrs, _ := strings.Cut(tag, ",")
	return name, func(yield func(string, string) bool) {
		for pair := range strings.SplitSeq(attrs, ",") {
			k, v, _ := strings.Cut(pair, ":")
			if !yield(strings.TrimSpace(k), strings.TrimSpace(v)) {
				return
			}
		}
	}, true
}
