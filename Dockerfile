# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
COPY --from=builder /build/target/taskapi.jar /app/app.jar
RUN chown spring:spring /app/app.jar
USER spring
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
