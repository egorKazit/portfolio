package com.yk.protfolio.springportfolio.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class CustomProperties {

    @Getter
    @Value("${portfolio.image.folder}")
    private String staticImageFolder;

}
