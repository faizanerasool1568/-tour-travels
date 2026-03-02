# -------- Build stage --------
FROM maven:3.8-openjdk-8 AS build

WORKDIR /app

ENV MAVEN_OPTS="-Xmx256m"

COPY . .

RUN mvn clean package -DskipTests

# -------- Run stage --------
FROM openjdk:8-jre-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-Xms128m","-Xmx256m","-jar","app.jar"]