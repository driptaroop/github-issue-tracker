# syntax=docker/dockerfile:experimental

FROM openjdk:11 AS build
ARG WORK_DIR=/workspace/app
ARG JAR_DIR=/workspace/jar
WORKDIR ${WORK_DIR}
COPY . ${WORK_DIR}
RUN --mount=type=cache,target=/root/.gradle ./gradlew -x clean build --no-daemon
COPY build/libs/github-issue-tracker-*.jar ${JAR_DIR}/app.jar


FROM gcr.io/distroless/java:11
EXPOSE $PORT
COPY --from=build /workspace/jar/app.jar .
ENV _JAVA_OPTIONS "-XX:MaxRAMPercentage=90 -Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true -Dfile.encoding=UTF-8"
CMD ["app.jar"]
