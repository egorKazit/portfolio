package com.yk.bot.kafka;

import com.yk.bot.service.MessageService;
import com.yk.processor.BotMessage;
import com.yk.schema.UserTopicAssignment;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BotMessageListener implements MessageListener<String, BotMessage> {

    private final List<UserTopicAssignment> userTopicAssignments;
    private final MessageService messageService;

    @Override
    public void onMessage(@NonNull ConsumerRecord<String, BotMessage> objectObjectConsumerRecord) {
        userTopicAssignments.stream()
                .filter(userTopicAssignment -> userTopicAssignment.getTopicName().equals(objectObjectConsumerRecord.topic()) &&
                        objectObjectConsumerRecord.value() != null)
                .forEach(userTopicAssignment -> messageService.execute(userTopicAssignment, objectObjectConsumerRecord.value()));
    }
}
