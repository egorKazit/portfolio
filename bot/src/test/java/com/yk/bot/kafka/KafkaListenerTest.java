package com.yk.bot.kafka;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.yk.bot.configuration.BotConfiguration;
import com.yk.bot.persistance.UserTopicDAO;
import com.yk.bot.service.MessageService;
import com.yk.processor.KafkaObjectDeserializer;
import com.yk.schema.User;
import com.yk.schema.UserTopicAssignment;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.listener.ContainerProperties;

class KafkaListenerTest {

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    void consumeMessage() {
        ContainerPropertiesTest containerProperties = new ContainerPropertiesTest("contact");
        ContainerPropertiesConfig containerPropertiesConfig = mock(ContainerPropertiesConfig.class);
        when(containerPropertiesConfig.containersPropertyBean()).thenReturn(containerProperties);
        when(containerPropertiesConfig.getConfig()).thenReturn(
                Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                        ConsumerConfig.GROUP_ID_CONFIG, "server.broadcast",
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaObjectDeserializer.class));

        UserTopicDAO userTopicDAO = mock(UserTopicDAO.class);
        BotConfiguration botConfiguration = mock(BotConfiguration.class);
        MessageService messageService = mock(MessageService.class);
        when(userTopicDAO.getUserTopicAssignments()).thenReturn(List.of(
                UserTopicAssignment.builder()
                        .id(0)
                        .user(User.builder().userId(0).id(0).build())
                        .topicName("contact")
                        .build()));
        when(botConfiguration.getKafkaUrl()).thenReturn("127.0.0.1:9092");
        when(botConfiguration.getKafkaClassTopicsMap()).thenReturn(Map.of("contact", "com.yk.protfolio.springportfolio.schema.Contact"));

        new KafkaListener(userTopicDAO, messageService, containerPropertiesConfig);
        Assertions.assertTrue(containerProperties.messageListener instanceof BotMessageListener);

    }

    private static class ContainerPropertiesTest extends ContainerProperties {

        private Object messageListener;

        public ContainerPropertiesTest(String... topics) {
            super(topics);
        }

        @Override
        public void setMessageListener(@NonNull Object messageListener) {
            super.setMessageListener(messageListener);
            this.messageListener = messageListener;
        }
    }

}