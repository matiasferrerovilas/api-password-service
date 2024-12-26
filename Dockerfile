FROM eclipse-temurin:21-jdk-alpine

ARG VERSION

WORKDIR /app

COPY build/libs/api-password-service-1.0.0-plain.jar api-password-service.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "api-password-service.jar"]
