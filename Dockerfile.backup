# syntax=docker/dockerfile:experimental

FROM azul/zulu-openjdk-alpine:11

EXPOSE $PORT

VOLUME /tmp

RUN addgroup -S demo && adduser -S demo -G demo
USER demo

COPY build/libs/github-issue-tracker-*.jar /app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "-noverify"]
