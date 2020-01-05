FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG JAR_FILE=swagger-spring-1.0.0.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]