# syntax=docker/dockerfile:experimental

FROM openjdk:11 AS build

ARG WORK_DIR=/workspace/app
ARG JAR_DIR=/workspace/jar
WORKDIR ${WORK_DIR}

COPY . ${WORK_DIR}

RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build

COPY build/libs/github-issue-tracker-*.jar ${JAR_DIR}/app.jar


FROM azul/zulu-openjdk-alpine:11

COPY --from=build /workspace/jar/app.jar .

ENTRYPOINT ["java", "-jar", "app.jar", "-noverify"]
