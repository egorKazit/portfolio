package com.yk.processor;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Log4j2
public class KafkaObjectDeserializer implements Deserializer<BotMessage> {
    @Override
    public BotMessage deserialize(String s, byte[] bytes) {
        try (
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return (BotMessage) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            log.atError().log("Error on parsing of data: {}", e.getMessage());
        }
        return null;
    }
}
