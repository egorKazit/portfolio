package com.yk.protfolio.springportfolio.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

public interface KafkaConfig {

    ProducerFactory<String, String> producerFactory();

    KafkaTemplate<String, String> kafkaTemplate();
}
