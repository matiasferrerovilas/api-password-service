FROM eclipse-temurin:21-jdk-alpine

ARG VERSION

WORKDIR /app

COPY ./api-password-service-1.0.0.jar api-password-service.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "api-password-service.jar"]
