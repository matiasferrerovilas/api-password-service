# Usa Eclipse Temurin como base
FROM eclipse-temurin:21-jdk-alpine

# Establece un argumento para la versión de la aplicación
ARG VERSION=latest

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /api-password-service

# Copia el archivo JAR correspondiente al contenedor
COPY build/libs/api-password-service-${VERSION}.jar api-password-service.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Configura el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "api-password-service.jar"]
