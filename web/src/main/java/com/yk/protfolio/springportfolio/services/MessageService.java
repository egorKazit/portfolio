package com.yk.protfolio.springportfolio.services;

import com.yk.processor.BotMessage;
import com.yk.schema.UserTopicAssignment;
import lombok.NonNull;

public interface MessageService {
    void execute(@NonNull UserTopicAssignment userTopicAssignment, @NonNull BotMessage botMessage);
}
