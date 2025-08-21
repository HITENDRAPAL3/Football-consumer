package com.consumer.Consumer.asyncMessagingLayer;

import com.consumer.Consumer.common.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;


@Configuration
public class KafkaConfig {

    private final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    @KafkaListener(topics = AppConstants.topicName, groupId = AppConstants.groupId)
    public void consumeMessages(String value) {
        logger.info("Received message: {}", value);
    }

}
