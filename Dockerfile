FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=./app.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local", "-Duser.timezone=Asia/Seoul", "/app.jar"]