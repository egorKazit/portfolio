package com.yk.bot.kafka;

import com.yk.bot.configuration.BotConfiguration;
import com.yk.processor.KafkaObjectDeserializer;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
public class ContainerPropertiesConfigImp implements ContainerPropertiesConfig {

    @Autowired
    private BotConfiguration botConfiguration;

    @Bean
    @Override
    public ContainerProperties containersPropertyBean() {
        return new ContainerProperties(botConfiguration.getKafkaClassTopicsMap().keySet().toArray(String[]::new));
    }

    @Override
    public Map<String, Object> getConfig() {
        return Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, botConfiguration.getKafkaUrl(),
                ConsumerConfig.GROUP_ID_CONFIG, "server.broadcast",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaObjectDeserializer.class);
    }

}
