FROM greboid/kotlin
COPY entrypoint.sh /entrypoint.sh
WORKDIR /app
RUN apk add --no-cache php7 php7-ctype bash && \
    chmod +x /entrypoint.sh && \
    chown -R nobody:nobody /entrypoint.sh && \
    mkdir -p /tmp/.gradle && \
    chown -R nobody:nobody /tmp
USER nobody
ENTRYPOINT ["/entrypoint.sh"]
