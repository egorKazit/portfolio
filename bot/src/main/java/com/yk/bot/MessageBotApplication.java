package com.yk.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.yk.bot", "com.yk.dao"})
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.yk.schema"})
public class MessageBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageBotApplication.class, args);
    }

}
