# =========================================================
# Dockerfile para desplegar "Formulario de contacto La Salle"
# en un servicio gratuito compatible con Docker (Render, etc).
#
# Build multi-etapa:
#   Etapa 1 (build): compila el proyecto con Maven y genera el JAR.
#   Etapa 2 (runtime): imagen final liviana, solo con el JRE y el JAR.
# =========================================================

# --- Etapa 1: compilación ---
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos primero el pom.xml para aprovechar el cache de capas de Docker
COPY pom.xml .
RUN mvn -q dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# --- Etapa 2: imagen final ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# El nombre del JAR coincide con <finalName> definido en pom.xml
COPY --from=build /app/target/formulario-contacto.jar app.jar

# Render (y Railway) inyectan la variable PORT automáticamente;
# application.properties ya usa server.port=${PORT:8080}
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
