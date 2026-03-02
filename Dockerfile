# -------- Build stage --------
FROM maven:3.8-openjdk-8 AS build

WORKDIR /app

# Copy all files to container
COPY . .

# Build the JAR
RUN mvn clean package -DskipTests

# -------- Run stage --------
FROM adoptopenjdk:8-jre-hotspot

WORKDIR /app

# Copy the JAR from build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]