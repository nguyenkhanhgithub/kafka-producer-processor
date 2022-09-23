package com.cmcglobal.kafkastreamprocessor.configs;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class TopicConfig {
    public static final String DOMAIN_EVENT_TOPIC = "dashboard.email.topic";
    public static final String HRMS_EVENT_TOPIC = "hrms.event.topic";
    public static final String JIRA_EVENT_TOPIC = "jira.event.topic";

    @Value("${spring.kafka.topic.num-partition}")
    private int numPartitions;

    @Value("${spring.kafka.topic.replication-factor}")
    private short replicationFactor;

    @Bean
    public NewTopic topic() {
        return new NewTopic(DOMAIN_EVENT_TOPIC, numPartitions, replicationFactor);
    }

    @Bean
    public NewTopic HrmsTopic() {
        return new NewTopic(HRMS_EVENT_TOPIC, numPartitions, replicationFactor);
    }

    @Bean
    public NewTopic JraTopic() {
        return new NewTopic(JIRA_EVENT_TOPIC, numPartitions, replicationFactor);
    }
}
