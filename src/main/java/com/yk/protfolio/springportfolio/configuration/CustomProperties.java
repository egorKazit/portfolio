package com.yk.protfolio.springportfolio.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration class for custom fields
 */
@Component
public class CustomProperties {

    @Getter
    @Value("${portfolio.image.folder:/tmp/static/images/}")
    private String staticImageFolder;

}
