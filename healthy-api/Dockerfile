# Use the official gradle image to create a build artifact.
# https://hub.docker.com/_/gradle
FROM gradle:6.5 as builder

# Copy local code to the container image.
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src ./src

# Build a release artifact.
RUN gradle clean bootJar --no-daemon
RUN ls -l /home/gradle/build/libs

# Use AdoptOpenJDK for base image.
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM adoptopenjdk:11.0.8_10-jre-hotspot

# Copy the jar to the production image from the builder stage.
COPY --from=builder /home/gradle/build/libs/*.jar /Healthy.jar
RUN mkdir /config
COPY src/main/resources/healthy.yaml /config/healthy.yaml
EXPOSE 8080
# Run the web service on container startup.
CMD [ "java", "-jar", "-Dhealthy.config.location=file:config/healthy.yaml", "/Healthy.jar" ]