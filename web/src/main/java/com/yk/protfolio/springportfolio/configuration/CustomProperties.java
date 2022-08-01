package com.yk.protfolio.springportfolio.configuration;

import java.util.Map;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration class for custom fields
 */
@Getter
@Component
public class CustomProperties {

    @Value("${portfolio.image.folder:/tmp/static/images/}")
    private String staticImageFolder;

    @Value("${portfolio.kafka.enabled:false}")
    private boolean isKafkaEnabled;

    @Value("${portfolio.kafka.url}")
    private String kafkaUrl;

    @Value("#{${portfolio.kafka.topics}}")
    private Map<String, String> kafkaClassTopicsMap;


}
