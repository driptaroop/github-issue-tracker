FROM amazoncorretto:11

EXPOSE $PORT

COPY build/libs/github-issue-tracker-*.jar /service.jar

CMD java -jar service.jar
