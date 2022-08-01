package com.yk.bot.kafka;

import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.yk.bot.configuration.BotConfiguration;
import com.yk.bot.persistance.UserTopicDAO;
import com.yk.bot.service.MessageService;
import com.yk.processor.BotMessage;
import com.yk.processor.KafkaObjectDeserializer;
import com.yk.processor.KafkaObjectSerializer;
import com.yk.schema.Contact;
import com.yk.schema.User;
import com.yk.schema.UserTopicAssignment;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest
//@RunWith(SpringRunner.class)
class KafkaListenerTest {

//    @InjectMocks
//    private KafkaListener kafkaListener;

    //    @Mock
//    private BotConfiguration botConfiguration;
//
//    //    @Mock
//    private static UserTopicDAO userTopicDAO;
//
//    //    @Mock
//    private MessageService messageService;
//
//    //    @BeforeAll
//    public static void beforeAll() {
//        userTopicDAO = Mockito.mock(UserTopicDAO.class);
//    }

    @BeforeEach
    public void beforeEach() {

    }

//    @Autowired
//    KafkaListenerTest(UserTopicDAO userTopicDAO) {
//        this.userTopicDAO = userTopicDAO;
//        beforeAll();
//    }
//
//    public void beforeAll() {
//
//    }

    @Test
    void consumeMessage() {
        ContainerPropertiesTest containerProperties = new ContainerPropertiesTest("contact");
        AtomicReference<MessageListener<String, BotMessage>> messageListener = new AtomicReference<>();
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
        public void setMessageListener(Object messageListener) {
            super.setMessageListener(messageListener);
            this.messageListener = messageListener;
        }
    }

}