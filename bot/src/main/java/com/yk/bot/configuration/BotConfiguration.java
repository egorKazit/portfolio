package com.yk.bot.configuration;

import java.util.Map;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Getter
public class BotConfiguration {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.kafka.url}")
    private String kafkaUrl;

    @Value("#{${portfolio.kafka.topics}}")
    private Map<String, String> kafkaClassTopicsMap;

}
