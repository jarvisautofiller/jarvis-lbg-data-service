# Use a base image for building the JAR file
FROM openjdk:17-jdk-slim AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper files and project files to the container
COPY gradlew .
COPY gradle gradle
COPY src src
COPY build.gradle .
COPY settings.gradle .

# Give execute permission to the Gradle wrapper
RUN chmod +x ./gradlew

# Run the Gradle build to create the JAR file
RUN ./gradlew clean build

# Use a new base image for running the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/jarvis-government-api-service.jar app.jar

# Set environment variables and expose the port
ENV HOST 0.0.0.0
EXPOSE 5050

ARG SPRING_PROFILES_ACTIVE=dev
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

# Define the entry point for the application
ENTRYPOINT ["sh", "-c", "java -Xmx512m -XX:+PrintFlagsFinal -XX:+PrintCommandLineFlags -jar /app/app.jar --server.port=${PORT:-5050}"]
