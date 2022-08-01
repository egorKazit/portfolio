package com.yk.bot.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.yk.processor.KafkaObjectDeserializer;
import java.util.List;
import java.util.Objects;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContainerPropertiesConfigImpTest {

    @Autowired
    ContainerPropertiesConfig containerPropertiesConfig;

    @Test
    void containersPropertyBean() {
        assertTrue(List.of(Objects.requireNonNull(containerPropertiesConfig.containersPropertyBean().getTopics())).contains("contact"));
    }

    @Test
    void getConfig() {
        assertTrue(containerPropertiesConfig.getConfig().containsKey(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertEquals("127.0.0.1:9092", containerPropertiesConfig.getConfig().get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertTrue(containerPropertiesConfig.getConfig().containsKey(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG));
        assertEquals(StringDeserializer.class, containerPropertiesConfig.getConfig().get(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG));
        assertTrue(containerPropertiesConfig.getConfig().containsKey(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG));
        assertEquals(KafkaObjectDeserializer.class, containerPropertiesConfig.getConfig().get(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG));
    }
}