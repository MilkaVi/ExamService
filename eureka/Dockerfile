FROM openjdk:17-alpine as java
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENV CONFIG_SERVER_URL=""
ENTRYPOINT ["java","-jar","/app.jar"]