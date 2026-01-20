package server

import (
	"context"
	"embed"
	"encoding/json"
	"errors"
	"io/fs"
	"log/slog"
	"net/http"
	"slices"
	"strconv"
	"strings"
	"time"

	"github.com/go-co-op/gocron/v2"
	"github.com/gorilla/mux"
	"github.com/samber/lo"

	"github.com/wjiec/leetcode-daily/internal/leetcode"
	"github.com/wjiec/leetcode-daily/internal/leetcode/automator"
)

//go:embed web
var web embed.FS

type Server struct {
	debug  bool
	config *string
	lang   []string

	cron gocron.Scheduler
	job  gocron.Job
}

func (s *Server) Start(ctx context.Context, addr string) error {
	r := mux.NewRouter()
	r.Handle("/", http.FileServerFS(lo.Must(fs.Sub(web, "web")))).Methods(http.MethodGet)
	r.HandleFunc("/api/status", s.apiStatus).Methods(http.MethodGet)
	r.HandleFunc("/api/cookies", s.apiResetCookies).Methods(http.MethodPost)
	r.HandleFunc("/api/_healthy", s.apiHealthy).Methods(http.MethodGet)

	hs := &http.Server{Handler: r, Addr: addr}
	go func() {
		<-ctx.Done()
		_ = hs.Shutdown(context.Background())
		_ = s.cron.Shutdown()
	}()

	s.cron.Start()
	return hs.ListenAndServe()
}

func (s *Server) apiHealthy(w http.ResponseWriter, _ *http.Request) {
	w.WriteHeader(http.StatusOK)
	_, _ = w.Write([]byte("ok"))
}

func (s *Server) apiStatus(w http.ResponseWriter, r *http.Request) {
	var username, combo, today, question string
	err := s.withClient(func(c *leetcode.Client) error {
		comboResp, err := c.ComboRecord(r.Context())
		if err != nil {
			return err
		} else if len(comboResp.TodayRecord) == 0 {
			return errors.New("no combo record")
		}
		combo = strconv.Itoa(comboResp.TodayRecord[0].ComboTimes)

		todayQuestion, err := c.TodayQuestion(r.Context())
		if err != nil {
			return err
		} else if len(todayQuestion.TodayRecord) == 0 {
			return errors.New("no today question")
		}
		today = todayQuestion.TodayRecord[0].UserStatus
		question = todayQuestion.TodayRecord[0].Question.Title

		username, err = c.Username()
		return err
	})
	if err != nil {
		slog.Warn("failed to fetch status", "error", err)
		w.WriteHeader(http.StatusInternalServerError)
		_, _ = w.Write(lo.Must(json.Marshal(map[string]any{"error": err.Error()})))
		return
	}

	w.Header().Set("Content-Type", "application/json")
	_, _ = w.Write(lo.Must(json.Marshal(map[string]any{
		"combo":    combo,
		"today":    today,
		"question": question,
		"username": username,
		"next":     lo.Must(s.job.NextRun()),
	})))
}

func (s *Server) apiResetCookies(w http.ResponseWriter, r *http.Request) {
	var body struct {
		Cookies string `json:"cookies"`
	}
	if err := json.NewDecoder(r.Body).Decode(&body); err != nil {
		w.WriteHeader(http.StatusBadRequest)
		_, _ = w.Write(lo.Must(json.Marshal(map[string]any{"error": err.Error()})))
		return
	}

	var jwtSession, csrfToken string
	for _, cookie := range strings.Split(body.Cookies, ";") {
		if name, value, ok := strings.Cut(cookie, "="); ok {
			switch strings.TrimSpace(name) {
			case leetcode.CookieJwtSession:
				jwtSession = strings.TrimSpace(value)
			case leetcode.CookieCsrfToken:
				csrfToken = strings.TrimSpace(value)
			}
		}
	}

	var username string
	err := s.withClient(func(c *leetcode.Client) (err error) {
		if username, err = c.Username(); err != nil {
			return err
		}

		slog.Info("resetting cookies", "username", username)
		return nil
	}, leetcode.WithCredential(csrfToken, jwtSession))
	if err != nil {
		slog.Warn("failed to reset cookies", "error", err)
		w.WriteHeader(http.StatusInternalServerError)
		_, _ = w.Write(lo.Must(json.Marshal(map[string]any{"error": err.Error()})))
		return
	}

	w.Header().Set("Content-Type", "application/json")
	_, _ = w.Write(lo.Must(json.Marshal(map[string]any{
		"username": username,
	})))
}

func (s *Server) dailySubmission() {
	err := s.withClient(func(c *leetcode.Client) error {
		defer func() { _ = automator.SaveCookies(c, *s.config) }()
		return automator.SubmitTodayQuestion(context.Background(), c, s.lang...)
	})
	if err != nil {
		slog.Warn("failed to submit today submission", "error", err)
	}
}

func (s *Server) withClient(f func(*leetcode.Client) error, extraOptions ...leetcode.Option) error {
	options := append(slices.Clone(extraOptions), leetcode.WithHttpTrace(s.debug))
	if r, err := automator.LoadFromConfig(*s.config); err != nil {
		return err
	} else if r != nil {
		options = append(options, leetcode.WithCookies(r))
	}

	c, err := leetcode.New(options...)
	if err != nil {
		return err
	}
	defer func() {
		if err := automator.SaveCookies(c, *s.config); err != nil {
			slog.Error("failed to save cookies", "error", err)
		}
	}()

	return f(c)
}

func NewServer(config *string, options ...Option) (*Server, error) {
	s := &Server{config: config}
	for _, applyOption := range options {
		if err := applyOption(s); err != nil {
			return nil, err
		}
	}

	cron, err := gocron.NewScheduler(gocron.WithLocation(time.Local))
	if err != nil {
		return nil, err
	}

	j, err := cron.NewJob(gocron.DailyJob(1, func() []gocron.AtTime {
		return []gocron.AtTime{
			gocron.NewAtTime(9, 30, 0),
			gocron.NewAtTime(12, 15, 0),
			gocron.NewAtTime(18, 30, 0),
		}
	}), gocron.NewTask(s.dailySubmission))
	if err != nil {
		return nil, err
	}
	s.cron = cron
	s.job = j

	return s, nil
}

type Option func(*Server) error

func WithDebug(debug bool) Option {
	return func(s *Server) error {
		s.debug = debug
		return nil
	}
}

func WithPreferenceLang(lang ...string) Option {
	return func(s *Server) error {
		s.lang = append(s.lang, lang...)
		return nil
	}
}
