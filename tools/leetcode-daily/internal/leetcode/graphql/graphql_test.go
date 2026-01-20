package graphql_test

import (
	"net/http"
	"testing"

	"github.com/imroc/req/v3"
	"github.com/jarcoal/httpmock"
	"github.com/stretchr/testify/assert"

	"github.com/wjiec/leetcode-daily/internal/leetcode/api"
	"github.com/wjiec/leetcode-daily/internal/leetcode/graphql"
)

const testEndpoint = "http://localhost/graphql/"

func TestNew(t *testing.T) {
	c, err := graphql.New[*api.TodayQuestion](testEndpoint, req.C())
	if assert.NoError(t, err) {
		assert.NotNil(t, c)
	}
}

func TestClient_Query(t *testing.T) {
	httpClient := req.C()
	httpmock.ActivateNonDefault(httpClient.GetClient())
	httpmock.RegisterResponder(http.MethodPost, testEndpoint, func(r *http.Request) (*http.Response, error) {
		body := `{"data": {"todayRecord": [{"date": "2026-01-01", "question": {"id": "2233", "title": "hello world"}}]}}`
		resp := httpmock.NewStringResponse(http.StatusOK, body)
		resp.Header.Set("Content-Type", "application/json")
		return resp, nil
	})

	if c, err := graphql.New[*api.TodayQuestion](testEndpoint, httpClient); assert.NoError(t, err) {
		resp, err := c.Query(t.Context(), map[string]any{})
		if assert.NoError(t, err) && assert.NotEmpty(t, resp.TodayRecord) {
			assert.Equal(t, resp.TodayRecord[0].Date, "2026-01-01")
			assert.Equal(t, resp.TodayRecord[0].Question.Title, "hello world")
		}
	}
}
