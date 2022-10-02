FROM maven:3.6.1-jdk-8-alpine AS build
ENV HOME=/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 mvn -f $HOME/pom.xml clean package

#
# Package stage
#
FROM openjdk:8u212-jdk-alpine
COPY --from=build /app/target/kafka-producer-processor.jar kafka-producer-processor.jar
EXPOSE 7001
ENTRYPOINT ["java", "-jar","kafka-producer-processor.jar"]