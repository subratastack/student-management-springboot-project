FROM ubuntu:latest
LABEL authors="dell"

# Use official Maven image to build the application
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /build

# Copy the Maven project descriptor files
COPY pom.xml ./pom.xml
COPY src ./src

# Resolve Maven dependencies and cache them
# RUN mvn dependency:go-offline

# Build the application using Maven
RUN mvn clean package -DskipTests

# Check the contents of the target directory
RUN ls -al ./target

# Use AdoptOpenJDK base image with JDK 17 for runtime
FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file from the build stage
COPY --from=build ./build/target/school-management-system-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that your application runs on
EXPOSE 8080

# Install curl (useful for health check scripts)
RUN apt-get update && apt-get install -y curl

# Install debug tools (optional, for debugging in container)
RUN apt-get install -y procps

# ENTRYPOINT allows you to configure a container that will run as an executable
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","-jar","/app/app.jar"]
