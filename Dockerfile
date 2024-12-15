# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle wrapper and build configuration files
COPY gradlew build.gradle settings.gradle /app/
COPY gradle /app/gradle/

# Copy the source code
COPY src /app/src

# Grant execution permission to Gradle wrapper
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build --no-daemon

# Expose the port the app runs on
EXPOSE 8080

ARG DATABASE_USERNAME
ARG DATABASE_PASSWORD

ENV DATABASE_USERNAME=${DATABASE_USERNAME}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}

# Run the application
CMD ["java", "-jar", "/app/build/libs/myJourneyCompanionDocker-0.0.1-SNAPSHOT.jar"]
