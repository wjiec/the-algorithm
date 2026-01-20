package utils

import (
	"errors"
)

func IgnoreError(err error, ignores ...error) error {
	for _, ignore := range ignores {
		if errors.Is(err, ignore) {
			return nil
		}
	}
	return nil
}
