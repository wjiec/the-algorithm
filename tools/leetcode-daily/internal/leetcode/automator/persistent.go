package automator

import (
	"bytes"
	"io"
	"os"

	"github.com/wjiec/leetcode-daily/internal/leetcode"
)

func SaveCookies(c *leetcode.Client, target string) error {
	if len(target) == 0 {
		return nil
	}

	fp, err := os.Create(target)
	if err != nil {
		return err
	}
	defer func() { _ = fp.Close() }()

	return c.SaveCookies(fp)
}

func LoadFromConfig(target string) (io.Reader, error) {
	fp, err := os.Open(target)
	if err != nil {
		if os.IsNotExist(err) {
			return nil, nil
		}
		return nil, err
	}
	defer func() { _ = fp.Close() }()

	content, err := io.ReadAll(fp)
	if err != nil {
		return nil, err
	}

	return bytes.NewReader(content), nil
}
