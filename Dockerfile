# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the WAR file into the container
COPY target/kordianspetitions-0.0.1-SNAPSHOT.war app.war

# Expose the port your application will run on
EXPOSE 9090

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.war"]
