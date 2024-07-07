# Use a base image with OpenJDK 21 installed
FROM openjdk:21-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container at /app
COPY app.jar app.jar

# Expose the port that the Spring Boot application uses (default is 8080)
EXPOSE 8080

# Run the JAR file when the container launches
CMD ["java", "-jar", "app.jar"]
