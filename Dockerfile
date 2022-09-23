
FROM openjdk:8
EXPOSE 7001
ADD target/kafka-producer-processor.jar kafka-producer-processor.jar
ENTRYPOINT ["java","-jar","/kafka-producer-processor.jar"]