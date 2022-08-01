package com.yk.protfolio.springportfolio.kafka;

import com.yk.processor.PostProcessQueue;
import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Service
@ConditionalOnProperty(value = "portfolio.kafka.enabled", matchIfMissing = true, havingValue = "true")
@Log4j2
public class KafkaQueueImp implements KafkaQueue {

    @Autowired
    private PostProcessQueue postProcessQueue;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private CustomProperties customProperties;

    @Override
    @Scheduled(fixedDelay = 500)
    public void sendData() {
        Object object;
        while ((object = postProcessQueue.getQueue().poll()) != null) {
            String className = object.getClass().getName();
            String topicName = customProperties.getKafkaClassTopicsMap().get(className);
            if (topicName != null)
                kafkaTemplate.send(topicName, object);
            else
                log.atError().log("Entry {} for class {} was skipped", object.toString(), className);
        }
    }


}
