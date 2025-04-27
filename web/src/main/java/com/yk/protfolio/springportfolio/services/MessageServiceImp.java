package com.yk.protfolio.springportfolio.services;

import com.yk.processor.BotMessage;
import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import com.yk.schema.UserTopicAssignment;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Setter
@Service
@Log4j2
public class MessageServiceImp extends TelegramLongPollingBot implements MessageService {

    public MessageServiceImp(CustomProperties customProperties) {
        super(customProperties.getToken());
    }

    @Override
    public String getBotUsername() {
        return "MessageNotifierSpringBot";
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    @Override
    public void execute(@NonNull UserTopicAssignment userTopicAssignment, @NonNull BotMessage botMessage) {
        try {
            execute(SendMessage.builder().chatId(String.valueOf(userTopicAssignment.getUser().getUserId()))
                    .text(botMessage.buildMessage()).build());
        } catch (TelegramApiException e) {
            log.atError().log("Error on message processing {}", e.getMessage());
        }
    }

}
