package api_test

import (
	"testing"

	"github.com/stretchr/testify/assert"

	"github.com/wjiec/leetcode-daily/internal/leetcode/api"
)

func TestError_Error(t *testing.T) {
	assert.NoError(t, (&api.Error{Message: ""}).Error())
	assert.Error(t, (&api.Error{Message: "something wrong"}).Error())
}
