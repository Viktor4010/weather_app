FROM openjdk:17-jdk-slim-buster

ENV GRADLE_VERSION=7.3

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle gradle

COPY src src

RUN ./gradlew build

EXPOSE 9095

CMD  ["java", "-jar", "/app/build/libs/weather_app_groovy-0.0.1-SNAPSHOT.jar"]