# Start with a base image that has OpenJDK 21 installed (Java Runtime Environment)
FROM openjdk:21-jdk-slim

# Information around who maintains the image
LABEL "org.opencontainers.image.authors"="andre.chaves"

# Copies the application's jar on target folder into the Docker image
# The jar file should be built and located in the target directory
# Ensure you have built the project with Maven or Gradle before building the Docker image
COPY target/loans-0.0.1-SNAPSHOT.jar loans-0.0.1-SNAPSHOT.jar

# Command to execute the application
ENTRYPOINT ["java", "-jar", "loans-0.0.1-SNAPSHOT.jar"]