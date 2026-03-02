# -------- Build stage --------
FROM adoptopenjdk:8-jdk-hotspot AS build

WORKDIR /app

# Copy all project files into Docker
COPY . .

# Build the Spring Boot JAR
RUN ./mvnw clean package -DskipTests

# -------- Run stage --------
FROM adoptopenjdk:8-jre-hotspot

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]