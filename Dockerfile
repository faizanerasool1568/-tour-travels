# -------- Build stage --------
FROM maven:3.8.8-jdk-8 AS build

WORKDIR /app

# Copy all project files
COPY . .

# Build the Spring Boot JAR
RUN mvn clean package -DskipTests

# -------- Run stage --------
FROM adoptopenjdk:8-jre-hotspot

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]