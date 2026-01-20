package main

import (
	"context"
	"log/slog"
	"net/http"
	"os"
	"os/signal"
	"strings"

	"github.com/adrg/xdg"
	"github.com/samber/lo"
	"github.com/spf13/cobra"

	"github.com/wjiec/leetcode-daily/cmd/daily/server"
	"github.com/wjiec/leetcode-daily/internal/leetcode"
	"github.com/wjiec/leetcode-daily/internal/leetcode/automator"
	"github.com/wjiec/leetcode-daily/internal/utils"
)

func main() {
	root := &cobra.Command{
		Use:           "leetcode-daily",
		Short:         "A tools for leetCode daily submission",
		SilenceUsage:  true,
		SilenceErrors: true,
	}

	var config string
	root.PersistentFlags().BoolP("debug", "d", false, "debug mode")
	root.PersistentFlags().StringVarP(&config, "config", "c", defaultConfigPath(), "config file path")
	root.PersistentFlags().StringArray("lang", envPreferenceLang(), "preference language to use")

	ctx, cancel := signal.NotifyContext(context.Background(), os.Interrupt)
	defer cancel()

	root.AddCommand(dailyCmd(&config), serverCmd(&config))
	if err := root.ExecuteContext(ctx); err != nil {
		slog.Error("failed to execute", "error", err)
	}
}

func dailyCmd(config *string) *cobra.Command {
	cmd := &cobra.Command{
		Use:   "daily",
		Short: "Manually submit daily submission",
	}

	var csrf, session string
	cmd.Flags().StringVar(&csrf, "csrf", "", "CSRF token")
	cmd.Flags().StringVar(&session, "session", "", "session token")
	cmd.RunE = func(cmd *cobra.Command, args []string) error {
		var options []leetcode.Option
		if len(csrf) != 0 && len(session) != 0 {
			options = append(options, leetcode.WithCredential(csrf, session))
		} else if len(*config) != 0 {
			if r, err := automator.LoadFromConfig(*config); err != nil {
				return err
			} else if r != nil {
				options = append(options, leetcode.WithCookies(r))
			}
		}

		debug := lo.Must(cmd.Root().PersistentFlags().GetBool("debug"))
		options = append(options, leetcode.WithHttpTrace(debug))

		c, err := leetcode.New(options...)
		if err != nil {
			return err
		}
		defer func() {
			if err := automator.SaveCookies(c, *config); err != nil {
				slog.Error("failed to save cookies", "error", err)
			}
		}()

		lang := lo.Must(cmd.Root().PersistentFlags().GetStringArray("lang"))
		return automator.SubmitTodayQuestion(cmd.Context(), c, lang...)
	}

	return cmd
}

func serverCmd(config *string) *cobra.Command {
	cmd := &cobra.Command{
		Use:   "server",
		Short: "Enable the http server",
		RunE: func(cmd *cobra.Command, args []string) error {
			debug := lo.Must(cmd.Root().PersistentFlags().GetBool("debug"))
			lang := lo.Must(cmd.Root().PersistentFlags().GetStringArray("lang"))

			srv, err := server.NewServer(config, server.WithDebug(debug), server.WithPreferenceLang(lang...))
			if err != nil {
				slog.Error("failed to create server", "err", err)
			}

			addr := lo.Must(cmd.PersistentFlags().GetString("listen"))
			slog.Info("the server has been started", "listen", addr)
			return utils.IgnoreError(srv.Start(cmd.Context(), addr), http.ErrServerClosed)
		},
	}

	cmd.PersistentFlags().StringP("listen", "l", utils.EnvOr("HTTP_ADDR", ":8080"), "The address to listen on")
	return cmd
}

func defaultConfigPath() string {
	xdgConfig, err := xdg.ConfigFile("leetcode-daily/auth.json")
	if err != nil {
		slog.Error("failed to get config path based on xdg", "err", err)
		xdgConfig = "/leetcode-daily/auth.json"
	}
	return utils.EnvOr("CONFIG_PATH", xdgConfig)
}

func envPreferenceLang() []string {
	if lang := utils.EnvOr("PREFERENCE_LANG", ""); len(lang) != 0 {
		return strings.Fields(lang)
	}
	return nil
}
