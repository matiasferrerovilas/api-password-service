FROM arm64v8/eclipse-temurin:21-jdk-alpine

ARG VERSION

WORKDIR /app

COPY ./api-password-service-${VERSION}.jar api-password-service.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "api-password-service.jar"]
