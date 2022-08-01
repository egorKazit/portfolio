package com.yk.bot.kafka;

import java.util.Map;
import org.springframework.kafka.listener.ContainerProperties;

public interface ContainerPropertiesConfig {
    ContainerProperties containersPropertyBean();

    Map<String, Object> getConfig();

}
