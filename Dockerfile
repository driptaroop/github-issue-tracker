FROM openjdk:11 AS cache
WORKDIR /app
ENV GRADLE_USER_HOME /cache
COPY build.gradle.kts gradle.properties settings.gradle.kts gradlew ./
COPY gradle ./gradle
RUN ls -ltr
RUN ./gradlew --no-daemon build --stacktrace


FROM openjdk:11 AS build

ARG WORK_DIR=/workspace/app
ARG JAR_DIR=/workspace/jar
COPY --from=cache /cache /home/gradle/.gradle
COPY . ${WORK_DIR}
WORKDIR ${WORK_DIR}

RUN ./gradlew clean build --no-daemon

COPY build/libs/github-issue-tracker-*.jar ${JAR_DIR}/app.jar



FROM azul/zulu-openjdk-alpine:11

COPY --from=build ${JAR_DIR}/app.jar .

ENTRYPOINT ["java", "-jar", "app.jar", "-noverify"]
