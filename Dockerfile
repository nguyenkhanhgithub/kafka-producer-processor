FROM maven:3.6.1-jdk-8-alpine AS build
WORKDIR /app
COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package

#
# Package stage
#
FROM openjdk:8u212-jdk-alpine
COPY --from=build /app/target/kafka-producer-processor.jar kafka-producer-processor.jar
EXPOSE 7001
ENTRYPOINT ["java", "-jar","kafka-producer-processor.jar"]