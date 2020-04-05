FROM openjdk:8-jdk-alpine
RUN addgroup -S bouncer && adduser -S bouncer -G bouncer
USER bouncer:bouncer
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]