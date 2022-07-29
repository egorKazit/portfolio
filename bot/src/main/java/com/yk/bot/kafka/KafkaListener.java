package com.yk.bot.kafka;

import com.yk.bot.configuration.BotConfiguration;
import com.yk.bot.persistance.UserTopicDAO;
import com.yk.bot.service.MessageService;
import com.yk.processor.BotMessage;
import com.yk.schema.UserTopicAssignment;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaListener {

    private static final KafkaMessageListenerContainer<String, Object> KAFKA_MESSAGE_CONTAINER;
    private static final ContainerProperties CONTAINER_PROPERTIES;
    private static final List<UserTopicAssignment> USER_TOPIC_ASSIGNMENTS;

    static {
        USER_TOPIC_ASSIGNMENTS = ComponentsWrapper.userTopicDAO.getUserTopicAssignments();
        CONTAINER_PROPERTIES = new ContainerProperties(ComponentsWrapper
                .botConfiguration
                .getKafkaClassTopicsMap()
                .keySet().toArray(String[]::new));
        CONTAINER_PROPERTIES.setMessageListener((MessageListener<String, BotMessage>) objectObjectConsumerRecord -> USER_TOPIC_ASSIGNMENTS.stream()
                .filter(userTopicAssignment -> userTopicAssignment.getTopicName().equals(objectObjectConsumerRecord.topic()) &&
                        objectObjectConsumerRecord.value() != null)
                .forEach(userTopicAssignment -> ComponentsWrapper.messageService.execute(userTopicAssignment, objectObjectConsumerRecord.value())));
        KAFKA_MESSAGE_CONTAINER = new KafkaMessageListenerContainer<>(new DefaultKafkaConsumerFactory<>(getConfig()), CONTAINER_PROPERTIES);
        KAFKA_MESSAGE_CONTAINER.start();
    }

    protected static Map<String, Object> getConfig() {
        return Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ComponentsWrapper.botConfiguration.getKafkaUrl(),
                ConsumerConfig.GROUP_ID_CONFIG, "server.broadcast",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ObjectDeserializer.class);
    }

    @Component
    private static class ComponentsWrapper {

        private static BotConfiguration botConfiguration;
        private static UserTopicDAO userTopicDAO;
        private static MessageService messageService;

        @Autowired
        private ComponentsWrapper(BotConfiguration botConfiguration, UserTopicDAO userTopicDAO, MessageService messageService) {
            ComponentsWrapper.botConfiguration = botConfiguration;
            ComponentsWrapper.userTopicDAO = userTopicDAO;
            ComponentsWrapper.messageService = messageService;
        }

    }


}
