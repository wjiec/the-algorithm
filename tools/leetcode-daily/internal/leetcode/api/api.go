package api

import (
	"errors"

	"github.com/samber/lo"
)

type Error struct {
	Message string `json:"message"`
}

func (e *Error) Error() error {
	if len(e.Message) == 0 {
		return nil
	}
	return errors.New(e.Message)
}

type Response[T any] struct {
	Errors []*Error `json:"errors"`
	Data   T        `json:"data"`
}

func (r *Response[T]) Error() error {
	return errors.Join(lo.Map(r.Errors, underlying[*Error]())...)
}

func underlying[T interface{ Error() error }]() func(T, int) error {
	return func(value T, index int) error {
		return value.Error()
	}
}
