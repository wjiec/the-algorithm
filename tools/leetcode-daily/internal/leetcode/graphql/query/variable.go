package query

import (
	"bytes"
	"reflect"
	"strings"

	"github.com/samber/lo"
)

type Empty struct{}

func encodeVariables(v any) (variables, error) {
	rt := indirect(reflect.TypeOf(v))
	if rt.Kind() != reflect.Struct {
		return nil, ErrNotStruct
	}

	var vars []*variable
	for i := 0; i < rt.NumField(); i++ {
		if ft := rt.Field(i); ft.IsExported() && !ft.Anonymous {
			if def, ok := parseVarTag(ft.Tag.Get("graphql")); ok {
				vars = append(vars, def)
			}
		}
	}

	return vars, nil
}

type variables []*variable

func (v variables) String() string {
	if len(v) == 0 {
		return ""
	}

	var out bytes.Buffer
	out.WriteString("(")
	out.WriteString(strings.Join(lo.Map(v, func(item *variable, index int) string {
		return item.String()
	}), ", "))
	out.WriteString(")")

	return out.String()
}

type variable struct {
	Name    string
	NotNull bool
	Array   bool
	Type    string
}

func (vd *variable) String() string {
	typename := vd.Type + lo.If(vd.NotNull, "!").Else("")
	if vd.Array {
		typename = "[" + typename + "]"
	}

	return vd.WrapName() + ": " + typename
}

func (vd *variable) WrapName() string {
	return "$" + vd.Name
}

func parseVarTag(tag string) (*variable, bool) {
	name, attrs, found := parseTag(tag)
	if !found {
		return nil, false
	}

	varDef := &variable{Name: name}
	for k, v := range attrs {
		switch k {
		case "notnull":
			varDef.NotNull = true
		case "type":
			varDef.Type = strings.TrimSpace(v)
		case "array":
			varDef.Array = true
		}
	}
	return varDef, true
}
