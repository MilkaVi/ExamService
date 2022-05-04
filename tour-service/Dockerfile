FROM openjdk:17-alpine as java
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]