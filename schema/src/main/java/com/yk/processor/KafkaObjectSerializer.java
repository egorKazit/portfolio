package com.yk.processor;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serializer;

@Log4j2
public class KafkaObjectSerializer implements Serializer<Object> {

    @Override
    public byte[] serialize(String s, Object o) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(o);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception ignored) {
            log.atError().log("can not convert object {}", o);
        }
        return new byte[0];
    }
}
