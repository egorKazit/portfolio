package com.yk.bot.kafka;

import com.yk.bot.persistance.UserTopicDAO;
import com.yk.bot.service.MessageService;
import com.yk.schema.UserTopicAssignment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
public class KafkaListener {

    private final List<UserTopicAssignment> userTopicAssignments;

    private final MessageService messageService;
    private final ContainerPropertiesConfig containerPropertiesConfig;

    @Autowired
    public KafkaListener(UserTopicDAO userTopicDAO, MessageService messageService, ContainerPropertiesConfig containerPropertiesConfig) {
        this.messageService = messageService;
        this.userTopicAssignments = userTopicDAO.getUserTopicAssignments();
        this.containerPropertiesConfig = containerPropertiesConfig;
        this.initToStart();
    }

    public void initToStart() {
        containerPropertiesConfig.containersPropertyBean()
                .setMessageListener(new BotMessageListener(userTopicAssignments, messageService));
        KafkaMessageListenerContainer<String, Object> kafkaMessageListenerContainer =
                new KafkaMessageListenerContainer<>(new DefaultKafkaConsumerFactory<>(containerPropertiesConfig.getConfig()),
                        containerPropertiesConfig.containersPropertyBean());
        kafkaMessageListenerContainer.start();
    }

}
