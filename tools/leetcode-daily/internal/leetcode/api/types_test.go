package api_test

import (
	"testing"

	"github.com/stretchr/testify/assert"

	"github.com/wjiec/leetcode-daily/internal/leetcode/api"
	"github.com/wjiec/leetcode-daily/internal/leetcode/graphql/query"
)

func Test_GraphqlEncode(t *testing.T) {
	t.Run((&api.TodayQuestion{}).QueryName(), func(t *testing.T) {
		encoded, err := query.Encode[*api.TodayQuestion, query.Empty]()
		if assert.NoError(t, err) {
			t.Log(string(encoded))
		}
	})
}
