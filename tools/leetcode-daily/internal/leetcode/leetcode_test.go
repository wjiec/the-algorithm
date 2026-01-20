package leetcode_test

import (
	"bytes"
	"strings"
	"testing"

	"github.com/imroc/req/v3"
	"github.com/stretchr/testify/assert"

	"github.com/wjiec/leetcode-daily/internal/leetcode"
)

func TestNew(t *testing.T) {
	t.Run("norm", func(t *testing.T) {
		c, err := leetcode.New()
		if assert.NoError(t, err) {
			assert.NotNil(t, c)
		}
	})

	t.Run("with options", func(t *testing.T) {
		c, err := leetcode.New(leetcode.WithHttpClient(req.C()))
		if assert.NoError(t, err) {
			assert.NotNil(t, c)
		}
	})
}

func TestClient_TodayQuestion(t *testing.T) {
	if c, err := leetcode.New(); assert.NoError(t, err) {
		todayQuestion, err := c.TodayQuestion(t.Context())
		if assert.NoError(t, err) {
			assert.NotEmpty(t, todayQuestion)
		}
	}
}

func TestClient_SaveCookies(t *testing.T) {
	if c, err := leetcode.New(); assert.NoError(t, err) {
		if _, err = c.TodayQuestion(t.Context()); assert.NoError(t, err) {
			var buf bytes.Buffer
			if err = c.SaveCookies(&buf); assert.NoError(t, err) {
				t.Log(buf.String())
				assert.NotEmpty(t, buf.String())
			}
		}
	}
}

func TestWithHttpClient(t *testing.T) {
	assert.NotNil(t, leetcode.WithHttpClient(req.C().ImpersonateSafari()))
}

func TestWithCookies(t *testing.T) {
	assert.NotNil(t, leetcode.WithCookies(strings.NewReader("[]")))
}

func TestWithCredential(t *testing.T) {
	assert.NotNil(t, leetcode.WithCredential("csrf-token", "leetcode-session"))
}
