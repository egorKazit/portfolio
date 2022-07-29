package com.yk.bot.service;

import com.yk.bot.configuration.BotConfiguration;
import com.yk.processor.BotMessage;
import com.yk.schema.UserTopicAssignment;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@Log4j2
public class MessageService extends TelegramLongPollingBot {

    @Autowired
    private BotConfiguration botConfiguration;

    @Override
    public String getBotUsername() {
        return "MessageNotifierSpringBot";
    }

    @Override
    public String getBotToken() {
        return botConfiguration.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.getMessage().isCommand()){

        }
    }

    public void execute(@NonNull UserTopicAssignment userTopicAssignment, @NonNull BotMessage botMessage) {
        try {
            execute(SendMessage.builder().chatId(String.valueOf(userTopicAssignment.getUser().getUserId()))
                    .text(botMessage.buildMessage()).build());
        } catch (TelegramApiException e) {
            log.atError().log("Error on message processing {}", e.getMessage());
        }
    }

}
