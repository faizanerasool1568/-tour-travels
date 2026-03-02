# -------- Build stage --------
FROM maven:3.8-openjdk-8 AS build

WORKDIR /app

# Limit Maven memory
ENV MAVEN_OPTS="-Xmx256m"

COPY . .

RUN mvn clean package -DskipTests

# -------- Run stage --------
FROM eclipse-temurin:8-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Limit Java memory to avoid 512MB crash
ENTRYPOINT ["java","-Xms128m","-Xmx256m","-jar","app.jar"]