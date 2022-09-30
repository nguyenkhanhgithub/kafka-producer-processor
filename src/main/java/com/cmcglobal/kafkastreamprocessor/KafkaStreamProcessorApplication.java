package com.cmcglobal.kafkastreamprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
@Slf4j
public class KafkaStreamProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamProcessorApplication.class, args);
        log.info("aaaaaaaaa");
    }

}
