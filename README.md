# Password Service Microservice

Este microservicio gestiona las contraseñas de los usuarios, permitiendo consultar y agregar nuevas contraseñas para el usuario autenticado. Se integra con Keycloak para la seguridad, Swagger para la documentación y Liquibase para gestionar las migraciones de base de datos.

## Características

- **Gestión de contraseñas**: Endpoints para obtener y guardar contraseñas del usuario autenticado.
- **Seguridad**: Integración con Keycloak para autenticación y autorización.
- **Documentación**: Documentación de la API con Swagger/OpenAPI.
- **Migraciones**: Gestión de cambios en la base de datos mediante Liquibase.
- **Estándares de código**: Configurado con Checkstyle y uso de Lombok para simplificar el código.

## Tecnologías y Dependencias

- **Spring Boot 3.3.4**
- **Spring Security & OAuth2 Resource Server**
- **Keycloak** (según [keycloak-adapter-bom](https://www.keycloak.org/))
- **SpringDoc OpenAPI 2.3.0**
- **Liquibase** para migraciones
- **Gradle** como herramienta de construcción
- **MapStruct** para mapeo de DTOs y entidades
- **JUnit/Spock** para pruebas
- **Caffeine** para caching
- **Feign** para clientes HTTP
- **MySQL** como base de datos (conector incluido)

## Requisitos Previos

- **Java 21**
- **Gradle** (o utilizar el wrapper incluido: `./gradlew`)
- Una instancia de **Keycloak** configurada
- Una base de datos MySQL (o la que prefieras) configurada y accesible

## Instalación

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/tuusuario/password-service.git



---

Este README proporciona una visión general del microservicio, detalla la configuración y dependencias (incluyendo la información del build.gradle), explica cómo construir y ejecutar la aplicación y describe los endpoints disponibles. Puedes personalizarlo según las necesidades de tu proyecto.
