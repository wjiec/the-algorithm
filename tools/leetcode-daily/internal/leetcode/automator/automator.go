package automator

import (
	"cmp"
	"context"
	"fmt"
	"log/slog"
	"math"
	"regexp"
	"slices"
	"strings"

	"github.com/wjiec/leetcode-daily/internal/leetcode"
	"github.com/wjiec/leetcode-daily/internal/leetcode/api"
)

var (
	ErrNoTodayQuestion       = fmt.Errorf("leetcode: no today question")
	ErrNoUserProvided        = fmt.Errorf("leetcode: no user provided")
	ErrNoAvailableSubmission = fmt.Errorf("leetcode: no available submission")
)

func SubmitTodayQuestion(ctx context.Context, c *leetcode.Client, preferenceLang ...string) error {
	if username, err := c.Username(); err == nil {
		slog.Info("current user", "username", username)
	}

	todayQuestion, err := c.TodayQuestion(ctx)
	if err != nil {
		return err
	} else if len(todayQuestion.TodayRecord) == 0 {
		return ErrNoTodayQuestion
	}

	todayRecord := todayQuestion.TodayRecord[0]
	slog.Info("today question", "title", todayRecord.Question.Title, "status", todayRecord.UserStatus)
	if len(todayRecord.UserStatus) == 0 {
		return ErrNoUserProvided
	} else if todayRecord.UserStatus == "FINISH" {
		slog.Info("today is finished")
		return nil
	}

	submissions, err := c.SubmissionList(ctx, todayRecord.Question.TitleSlug)
	if err != nil {
		return err
	}

	for _, submission := range submissions.SubmissionList.Submissions {
		if submission.Status != "AC" {
			continue
		}
		slog.Info("found submission", "lang", submission.LangName, "status", submission.Status, "id", submission.Id)

		submissionDetail, err := c.SubmissionDetail(ctx, submission.Id)
		if err != nil {
			slog.Warn("failed to get submission detail", "error", err)
			continue
		}

		detail := submissionDetail.SubmissionDetail
		slog.Info("resubmitting submission", "lang", detail.Lang, "memory", detail.MemoryPercentile, "runtime", detail.RuntimePercentile)
		if err = c.Submit(ctx, todayRecord.Question.Id, todayRecord.Question.TitleSlug, detail.Lang, detail.Code); err != nil {
			slog.Warn("failed to submit submission", "error", err)
			continue
		}
		return nil
	}
	return FindDiscussToSubmit(ctx, c, todayQuestion, preferenceLang...)
}

func FindDiscussToSubmit(ctx context.Context, c *leetcode.Client, todayQuestion *api.TodayQuestion, preferenceLang ...string) error {
	question := todayQuestion.TodayRecord[0].Question
	questionDetail, err := c.QuestionDetail(ctx, question.TitleSlug)
	if err != nil {
		return err
	}
	languageList := questionDetail.LanguageList

	topics, err := c.QuestionTopics(ctx, question.TitleSlug)
	if err != nil {
		return err
	}

	for _, topic := range topics.QuestionSolutionArticles.Edges {
		if !topic.Node.ByLeetcode {
			continue
		}

		detail, err := c.TopicDetail(ctx, topic.Node.Slug)
		if err != nil {
			slog.Warn("failed to fetch topic detail", "slug", topic.Node.Slug, "error", err)
			continue
		}

		solutions, err := extractSolutions(detail.SolutionArticle.Content, preferenceLang...)
		if err != nil {
			slog.Warn("failed to extract solutions", "slug", topic.Node.Slug, "error", err)
			continue
		}

		for _, solution := range solutions {
			var langName = solution.Lang
			for _, lang := range languageList {
				if lang.VerboseName == solution.Lang {
					langName = lang.Name
				}
			}

			if err = c.Submit(ctx, question.Id, question.TitleSlug, langName, strings.Join(solution.Code, "\n")); err != nil {
				slog.Warn("failed to submit submission", "error", err)
				continue
			}
			return nil
		}
	}
	return ErrNoAvailableSubmission
}

var (
	codeBlockStartRe = regexp.MustCompile("^```(.*?)\\s+(\\[sol\\d+-.*?])\\s*")
	codeBlockEndRe   = regexp.MustCompile("^```\\s*")
)

func extractSolutions(content string, preferenceLang ...string) (solutions []*Solution, err error) {
	var solution *Solution
	for _, line := range strings.Split(content, "\n") {
		if solution != nil {
			if codeBlockEndRe.MatchString(line) {
				solutions = append(solutions, solution)
				solution = nil
			} else {
				solution.Code = append(solution.Code, line)
			}
			continue
		}

		if matches := codeBlockStartRe.FindStringSubmatch(line); matches != nil {
			solution = &Solution{Lang: matches[1]}
		}
	}

	langWeight := map[string]int{}
	for i, lang := range preferenceLang {
		langWeight[strings.ToLower(lang)] = math.MaxInt32 - i
	}

	slices.SortFunc(solutions, func(a, b *Solution) int {
		return cmp.Compare(langWeight[strings.ToLower(b.Lang)], langWeight[strings.ToLower(a.Lang)])
	})
	return
}

type Solution struct {
	Code []string
	Lang string
}
