package query_test

import (
	"testing"

	"github.com/stretchr/testify/assert"

	"github.com/wjiec/leetcode-daily/internal/leetcode/graphql/query"
)

type Foobar struct {
	Name   int `graphql:"nickname,alias:name" json:"name"`
	Nested struct {
		Id string `graphql:"id" json:"id"`
	} `graphql:"nested" json:"nested"`
	Foo string  `graphql:"foo" json:"foo"`
	Bar float64 `graphql:"bar" json:"bar"`
}

func (f *Foobar) QueryName() string {
	return "foobar"
}

type Complex struct {
	Complex struct {
		Foo    string  `graphql:"foo" json:"foo"`
		Bar    float64 `graphql:"bar" json:"bar"`
		Inline struct {
			Baz float64 `graphql:"baz" json:"baz"`
		} `graphql:"inline,inline" json:",inline"`
	} `graphql:"complex,var-args" json:"complex"`
}

func (c *Complex) QueryName() string {
	return "complex"
}

type ComplexVariables struct {
	Qux string `graphql:"qux,type:String,notnull" json:"qux"`
	Xyz int    `graphql:"xyz,type:Int" json:"xyz"`
}

func TestEncode(t *testing.T) {
	t.Run("norm", func(t *testing.T) {
		if q, err := query.Encode[*Foobar, query.Empty](); assert.NoError(t, err) {
			t.Log(string(q))
		}
	})

	t.Run("complex", func(t *testing.T) {
		if q, err := query.Encode[*Complex, *ComplexVariables](); assert.NoError(t, err) {
			t.Log(string(q))
		}
	})
}
