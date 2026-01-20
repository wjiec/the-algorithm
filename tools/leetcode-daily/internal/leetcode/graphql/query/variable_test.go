package query

import (
	"strings"
	"testing"

	"github.com/stretchr/testify/assert"
)

func Test_encodeVariables(t *testing.T) {
	type Variables struct {
		Foo int    `graphql:"foo,notnull,type:Int" json:"foo"`
		Bar string `graphql:"bar,type:String" json:"bar"`
	}

	if vs, err := encodeVariables(&Variables{}); assert.NoError(t, err) {
		assert.True(t, strings.HasPrefix(vs.String(), `(`))
		assert.True(t, strings.HasSuffix(vs.String(), `)`))
	}
}
