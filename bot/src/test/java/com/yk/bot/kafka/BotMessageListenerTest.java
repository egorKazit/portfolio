package com.yk.bot.kafka;

import static org.junit.jupiter.api.Assertions.*;

import com.yk.bot.service.MessageService;
import com.yk.processor.BotMessage;
import com.yk.schema.User;
import com.yk.schema.UserTopicAssignment;
import java.util.List;
import lombok.NonNull;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;

class BotMessageListenerTest {

    @Test
    void onMessage() {
        BotMessageListener messageListener = new BotMessageListener(List.of(UserTopicAssignment.builder()
                .id(0)
                .user(User.builder().id(0).userId(0).build())
                .topicName("contact").build()), new MessageService() {
            @Override
            public void execute(@NonNull UserTopicAssignment userTopicAssignment, @NonNull BotMessage botMessage) {
                assertEquals("test", botMessage.buildMessage());
            }
        });
        messageListener.onMessage(new ConsumerRecord<>("test", 0, 0L, "contact", () -> "test"));
    }
}