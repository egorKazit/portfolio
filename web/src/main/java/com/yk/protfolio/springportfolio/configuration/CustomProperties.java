package com.yk.protfolio.springportfolio.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Configuration class for custom fields
 */
@Getter
@Component
public class CustomProperties {

    @Value("${portfolio.image.folder:/tmp/static/images/}")
    private String staticImageFolder;

    @Value("${bot.token}")
    private String token;

}
