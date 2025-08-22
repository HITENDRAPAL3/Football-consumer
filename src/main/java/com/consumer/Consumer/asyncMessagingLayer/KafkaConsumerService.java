package com.consumer.Consumer.asyncMessagingLayer;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import com.consumer.Consumer.DomainLayer.DTO.MatchEventPlayerDTOs.MatchEventPlayerDTO;
import com.consumer.Consumer.GatewayLayer.implementation.PushEventsDelegate;
import com.consumer.Consumer.common.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class KafkaConsumerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Inject
    private PushEventsDelegate pushEventsDelegate;

    @KafkaListener(topics = AppConstants.topicName, groupId = AppConstants.groupId)
    public void consumeMessages(MatchCommentDTO value) {
        try {
            logger.info("Received message: {}", value);
            pushEventsDelegate.pushEventsForMatchComments(value);
        } catch (Exception e) {
            logger.error("Error processing message: {}", e.getMessage(), e);
        }
    }

    public void consumeMessages(MatchEventPlayerDTO value) {
        try {
            logger.info("Received message: {}", value);
            pushEventsDelegate.pushEventsForMatchEvents(value);
        } catch (Exception e) {
            logger.error("Error processing message: {}", e.getMessage(), e);
        }
    }
}