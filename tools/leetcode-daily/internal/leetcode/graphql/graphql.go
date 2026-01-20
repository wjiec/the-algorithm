package graphql

import (
	"context"

	"github.com/imroc/req/v3"

	"github.com/wjiec/leetcode-daily/internal/leetcode/api"
	"github.com/wjiec/leetcode-daily/internal/leetcode/graphql/query"
)

type Client[T query.Querier, V any] struct {
	query    string
	http     *req.Client
	endpoint string
}

func New[T query.Querier, V any](endpoint string, c *req.Client) (*Client[T, V], error) {
	qs, err := query.Encode[T, V]()
	if err != nil {
		return nil, err
	}

	return &Client[T, V]{http: c, query: string(qs), endpoint: endpoint}, nil
}

func (c *Client[T, V]) Query(ctx context.Context, variables V) (data T, err error) {
	resp, err := c.http.R().
		SetContext(ctx).
		SetSuccessResult(&api.Response[T]{}).
		SetBodyJsonMarshal(map[string]any{
			"query":         c.query,
			"variables":     variables,
			"operationName": data.QueryName(),
		}).
		Post(c.endpoint)
	if err != nil {
		return data, err
	}
	defer func() { _ = resp.Body.Close() }()

	apiResp := resp.SuccessResult().(*api.Response[T])
	if err = apiResp.Error(); err != nil {
		return data, err
	}

	return apiResp.Data, nil
}
