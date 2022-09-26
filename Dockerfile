#### Stage 1: Build the application
FROM openjdk:8-jdk-alpine AS build-stage

# Set the current working directory inside the image
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

RUN chmod +x mvnw
RUN sed -i 's/\r$//' mvnw

# Build all the dependencies in preparation to go offline.
# This is a separate step so the dependencies will be cached unless
# the pom.xml file has changed.
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src

# Package the application
RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#### Stage 2: A minimal docker image with command to run the app
FROM openjdk:8-jre-alpine

ARG DEPENDENCY=/app/target/dependency

# Copy project dependencies from the build stage
COPY --from=build-stage ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build-stage ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build-stage ${DEPENDENCY}/BOOT-INF/classes /app

EXPOSE 7001
ENTRYPOINT ["java", "-jar","kafka-producer-processor.jar"]