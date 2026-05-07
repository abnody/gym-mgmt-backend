# # ── Stage 1: Build ────────────────────────────────────────────────────────────
# FROM maven:3.9.15-eclipse-temurin-17-alpine AS builder
# WORKDIR /app
# COPY pom.xml .
# COPY src ./src
# RUN mvn package -DskipTests -q

# # ── Stage 2: Runtime ──────────────────────────────────────────────────────────
# FROM eclipse-temurin:17-jre-alpine
# WORKDIR /app
# RUN addgroup -S gymapp && adduser -S gymapp -G gymapp
# USER gymapp
# COPY --from=builder /app/target/*.jar app.jar
# ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]