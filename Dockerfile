FROM alpine:3.8
COPY entrypoint.sh /entrypoint.sh
WORKDIR /app
RUN apk add --no-cache php7 php7-ctype bash && \
    chmod +x /entrypoint.sh && \
    chown -R nobody:nobody /entrypoint.sh

USER nobody

ENTRYPOINT ["/entrypoint.sh"]
