FROM alpine:3.8
COPY entrypoint.sh /entrypoint.sh
WORKDIR /app
RUN apk add --no-cache php7 && \
    chmod +x /entrypoint.sh && \
    chown -R nobody:nobody /entrypoint.sh

USER nobody

ENTRYPOINT ["/entrypoint.sh"]
