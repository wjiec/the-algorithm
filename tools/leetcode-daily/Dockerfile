FROM golang:alpine AS builder

WORKDIR /workspace
ADD go.mod go.sum ./
RUN go mod download

ADD . .
RUN CGO_ENABLED=0 go build -o bin/leetcode-daily ./cmd/daily/main.go


FROM alpine

ENV TZ=Asia/Shanghai
ENV CONFIG_PATH=/leetcode-daily/auth.json
RUN apk add --no-cache tzdata

COPY --from=builder /workspace/bin/leetcode-daily /local/bin/leetcode-daily

ENTRYPOINT ["/local/bin/leetcode-daily", "server"]
