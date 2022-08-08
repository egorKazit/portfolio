package com.yk.protfolio.springportfolio.kafka;

import com.yk.processor.KafkaObjectSerializer;
import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfigImp implements KafkaConfig {

    @Autowired
    private CustomProperties customProperties;

    @Override
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                customProperties.getKafkaUrl());
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                KafkaObjectSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    @ConditionalOnProperty(value = "portfolio.kafka.enabled", havingValue = "true")
    @Override
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    @ConditionalOnProperty(value = "portfolio.kafka.enabled", havingValue = "true")
    public KafkaAdmin.NewTopics newTopics(KafkaAdmin defaultKafkaAdmin) {
        defaultKafkaAdmin.setAutoCreate(false);
//        KafkaAdmin kafkaAdmin = new KafkaAdmin(getConfig());
//
//        kafkaAdmin.createOrModifyTopics(new HashSet<>(customProperties.getKafkaClassTopicsMap().values())
//                .stream().map(topicName ->
//                        TopicBuilder.name(topicName)
//                                .configs(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
//                                        customProperties.getKafkaUrl()))
//                                .partitions(1)
//                                .build()).collect(Collectors.toList()).toArray(NewTopic[]::new));

        return new KafkaAdmin.NewTopics(new HashSet<>(customProperties.getKafkaClassTopicsMap().values())
                .stream().map(topicName ->
                        TopicBuilder.name(topicName)
                                .configs(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                                        customProperties.getKafkaUrl()))
                                .partitions(1)
                                .build()).collect(Collectors.toList()).toArray(NewTopic[]::new));
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(getConfig());
    }

    private Map<String, Object> getConfig() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                customProperties.getKafkaUrl());
        return configProps;
    }

}
