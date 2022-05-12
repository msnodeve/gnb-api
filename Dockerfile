FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=./gnb-api-1.0.0.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local", "-Duser.timezone=Asia/Seoul", "/app.jar"]