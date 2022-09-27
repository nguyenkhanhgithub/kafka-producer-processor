package com.cmcglobal.kafkastreamprocessor.producers;

import com.cmcglobal.dashboard.avro.DashboardEmailValue;
import com.cmcglobal.hrms.avro.HrmsRequestValueAvro;
import com.cmcglobal.jira.avro.JiraTicketValueAvro;
import com.cmcglobal.kafkastreamprocessor.configs.TopicConfig;
import com.cmcglobal.kafkastreamprocessor.entities.Example;
import com.cmcglobal.kafkastreamprocessor.entities.HrmsRequestValue;
import com.cmcglobal.kafkastreamprocessor.entities.JiraTicketValue;
import com.cmcglobal.kafkastreamprocessor.mappers.EntityMapper;
import com.cmcglobal.kafkastreamprocessor.utils.MessagePack;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@Log4j2
public class ProducerExampleImpl implements BaseProducerInterface {

    @Autowired
    protected KafkaTemplate<String, DashboardEmailValue> kafkaTemplate;

    @Autowired
    protected KafkaTemplate<String, HrmsRequestValueAvro> kafkaTemplateHRms;

    @Autowired
    protected KafkaTemplate<String, JiraTicketValueAvro> kafkaTemplateJira;
    @Autowired protected EntityMapper entityMapper;

    @Override
//    @Scheduled(cron = "* 0/10 * * * *")
    public void producer() {
        DashboardEmailValue exampleAvro = new DashboardEmailValue();
        exampleAvro.setId(100);
        exampleAvro.setContent("nguyen khanh");
        exampleAvro.setCreatedAt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        exampleAvro.setType("MAIL_BILLABLE");
        exampleAvro.setVersion(1);
        kafkaTemplate.send(TopicConfig.DOMAIN_EVENT_TOPIC, exampleAvro);
        log.info("success {}", exampleAvro);
    }

//    @Scheduled(cron = "0/5 * * * * *")
    public void hrmsProducer() {
        HrmsRequestValueAvro avro = new HrmsRequestValueAvro();
        HrmsRequestValue value = new HrmsRequestValue();
        avro.setId(1);
        avro.setContent(ByteBuffer.wrap(MessagePack.objectToBytea(value)));
        avro.setCreatedAt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        avro.setSystem("HRMS");
        kafkaTemplateHRms.send(TopicConfig.HRMS_EVENT_TOPIC, avro);
    }

//    @Scheduled(cron = "0/5 * * * * *")
    public void jiraProducer() {
        JiraTicketValueAvro avro = new JiraTicketValueAvro();
        JiraTicketValue value = new JiraTicketValue();
        avro.setId(1);
        avro.setContent(ByteBuffer.wrap(MessagePack.objectToBytea(value)));
        avro.setCreatedAt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        avro.setSystem("JIRA");
        kafkaTemplateJira.send(TopicConfig.JIRA_EVENT_TOPIC, avro);
    }
}
