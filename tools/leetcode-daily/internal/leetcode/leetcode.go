package leetcode

import (
	"context"
	"encoding/json"
	"errors"
	"fmt"
	"io"
	"net/http"
	"net/http/cookiejar"
	"net/url"
	"strings"

	"github.com/golang-jwt/jwt/v5"
	"github.com/google/uuid"
	"github.com/imroc/req/v3"
	"github.com/samber/lo"

	"github.com/wjiec/leetcode-daily/internal/leetcode/api"
	"github.com/wjiec/leetcode-daily/internal/leetcode/graphql"
	"github.com/wjiec/leetcode-daily/internal/leetcode/graphql/query"
)

const (
	baseURL         = "https://leetcode.cn"
	graphqlEndpoint = baseURL + "/graphql/"

	CookieJwtSession = "LEETCODE_SESSION"
	CookieCsrfToken  = "csrftoken"
)

type Client struct {
	jar  http.CookieJar
	http *req.Client
}

func (c *Client) Username() (string, error) {
	return c.claimFromJwtSession(func(claims jwt.MapClaims) (string, error) {
		if raw, ok := claims["username"]; ok {
			if username, ok := raw.(string); ok {
				return username, nil
			}
		}
		return "", errors.New("username not found in claims")
	})
}

func (c *Client) DeviceId() (string, error) {
	return c.claimFromJwtSession(func(claims jwt.MapClaims) (string, error) {
		if raw, ok := claims["device_id"]; ok {
			if deviceId, ok := raw.(string); ok {
				return deviceId, nil
			}
		}
		return "", errors.New("device_id not found in claims")
	})
}

func (c *Client) claimFromJwtSession(f func(jwt.MapClaims) (string, error)) (string, error) {
	for _, cookie := range c.jar.Cookies(lo.Must(url.Parse(baseURL))) {
		if cookie.Name == CookieJwtSession {
			token, _, err := jwt.NewParser().ParseUnverified(cookie.Value, &jwt.MapClaims{})
			if err != nil {
				return "", err
			}

			return f(*token.Claims.(*jwt.MapClaims))
		}
	}
	return "", errors.New("jwt session not found in cookies")
}

func (c *Client) TodayQuestion(ctx context.Context) (*api.TodayQuestion, error) {
	g, err := graphql.New[*api.TodayQuestion, query.Empty](graphqlEndpoint, c.http)
	if err != nil {
		return nil, err
	}
	return g.Query(ctx, query.Empty{})
}

func (c *Client) ComboRecord(ctx context.Context) (*api.ComboRecord, error) {
	g, err := graphql.New[*api.ComboRecord, query.Empty](graphqlEndpoint, c.http)
	if err != nil {
		return nil, err
	}
	return g.Query(ctx, query.Empty{})
}

func (c *Client) SubmissionList(ctx context.Context, slug string) (*api.SubmissionList, error) {
	g, err := graphql.New[*api.SubmissionList, *api.SubmissionListVariables](graphqlEndpoint, c.http)
	if err != nil {
		return nil, err
	}
	return g.Query(ctx, &api.SubmissionListVariables{Offset: 0, Limit: 20, QuestionSlug: &slug})
}

func (c *Client) SubmissionDetail(ctx context.Context, id string) (*api.SubmissionDetail, error) {
	g, err := graphql.New[*api.SubmissionDetail, *api.SubmissionDetailVariables](graphqlEndpoint, c.http)
	if err != nil {
		return nil, err
	}
	return g.Query(ctx, &api.SubmissionDetailVariables{SubmissionId: id})
}

func (c *Client) QuestionDetail(ctx context.Context, slug string) (*api.QuestionDetail, error) {
	g, err := graphql.New[*api.QuestionDetail, *api.QuestionDetailVariables](graphqlEndpoint, c.http)
	if err != nil {
		return nil, err
	}
	return g.Query(ctx, &api.QuestionDetailVariables{TitleSlug: slug})
}

func (c *Client) QuestionTopics(ctx context.Context, slug string) (*api.QuestionTopics, error) {
	g, err := graphql.New[*api.QuestionTopics, *api.QuestionTopicsVariables](graphqlEndpoint, c.http)
	if err != nil {
		return nil, err
	}
	return g.Query(ctx, &api.QuestionTopicsVariables{
		QuestionSlug: slug,
		Skip:         0,
		First:        15,
		OrderBy:      "DEFAULT",
		UserInput:    "",
		TagSlugs:     make([]string, 0),
	})
}

func (c *Client) TopicDetail(ctx context.Context, slug string) (*api.DiscussTopic, error) {
	g, err := graphql.New[*api.DiscussTopic, *api.DiscussTopicVariables](graphqlEndpoint, c.http)
	if err != nil {
		return nil, err
	}
	return g.Query(ctx, &api.DiscussTopicVariables{Slug: slug})
}

func (c *Client) Submit(ctx context.Context, questionId, slug, lang, code string) error {
	resp, err := c.http.R().
		SetContext(ctx).
		SetSuccessResult(&api.Response[any]{}).
		SetBodyJsonMarshal(map[string]any{
			"lang":        lang,
			"question_id": questionId,
			"typed_code":  code,
		}).
		Post(fmt.Sprintf("%s/problems/%s/submit/", baseURL, slug))
	if err != nil {
		return err
	}
	defer func() { _ = resp.Body.Close() }()

	apiResp := resp.SuccessResult().(*api.Response[any])
	if err = apiResp.Error(); err != nil {
		return err
	}
	return nil
}

func (c *Client) SaveCookies(w io.Writer) error {
	cookies := c.jar.Cookies(lo.Must(url.Parse(graphqlEndpoint)))
	return json.NewEncoder(w).Encode(cookies)
}

func (c *Client) beforeSendQuery() req.RequestMiddleware {
	return func(rc *req.Client, req *req.Request) error {
		for _, cookie := range c.jar.Cookies(lo.Must(url.Parse(baseURL))) {
			if cookie.Name == CookieCsrfToken {
				req.Headers.Set("X-Csrftoken", cookie.Value)
			}
		}

		req.Headers.Set("Accept", "*/*")
		req.Headers.Set("uuuserid", lo.Must(c.DeviceId()))
		req.Headers.Set("random-uuid", uuid.NewString())
		return nil
	}
}

func New(options ...Option) (*Client, error) {
	jar, err := cookiejar.New(nil)
	if err != nil {
		return nil, err
	}

	c := &Client{jar: jar}
	c.http = req.C().ImpersonateChrome().SetCookieJar(jar).OnBeforeRequest(c.beforeSendQuery())
	for _, applyOption := range options {
		if err := applyOption(c); err != nil {
			return nil, err
		}
	}
	return c, nil
}

type Option func(*Client) error

func WithHttpClient(http *req.Client) Option {
	return func(c *Client) error {
		c.http = http.OnBeforeRequest(c.beforeSendQuery())
		return nil
	}
}

func WithCookies(r io.Reader) Option {
	return func(c *Client) error {
		var cookies []*http.Cookie
		if err := json.NewDecoder(r).Decode(&cookies); err != nil {
			return err
		}

		c.jar.SetCookies(lo.Must(url.Parse(baseURL)), cookies)
		return nil
	}
}

func WithRawCookies(cookies string) Option {
	return func(c *Client) error {
		for _, cookie := range strings.Split(cookies, ";") {
			if name, value, ok := strings.Cut(cookie, "="); ok {
				c.jar.SetCookies(lo.Must(url.Parse(baseURL)), []*http.Cookie{
					{Name: strings.TrimSpace(name), Value: strings.TrimSpace(value)},
				})
			}
		}
		return nil
	}
}

func WithCredential(csrfToken, jwtSession string) Option {
	return func(c *Client) error {
		c.jar.SetCookies(lo.Must(url.Parse(baseURL)), []*http.Cookie{
			{Name: CookieCsrfToken, Value: csrfToken},
			{Name: CookieJwtSession, Value: jwtSession},
		})

		return nil
	}
}

func WithHttpTrace(debug bool) Option {
	return func(c *Client) error {
		if debug {
			c.http.DevMode()
		}
		return nil
	}
}
