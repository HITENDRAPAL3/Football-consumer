package com.consumer.Consumer.asyncMessagingLayer;

import com.consumer.Consumer.MatchCommentDTOs.MatchCommentDTO;
import com.consumer.Consumer.common.AppConstants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfig {

    private final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    @Bean
    public ConsumerFactory<String, MatchCommentDTO> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, AppConstants.groupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.consumer.Consumer.MatchCommentDTOs,com.football.football.DomainLayer.DTO.MatchCommentDTOs");
        configProps.put(JsonDeserializer.TYPE_MAPPINGS, "com.football.football.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO:com.consumer.Consumer.MatchCommentDTOs.MatchCommentDTO");
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MatchCommentDTO.class.getName());
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MatchCommentDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MatchCommentDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = AppConstants.topicName, groupId = AppConstants.groupId)
    public void consumeMessages(MatchCommentDTO value) {
        try {
            logger.info("Received message: {}", value);
            // Add your business logic here
        } catch (Exception e) {
            logger.error("Error processing message: {}", e.getMessage(), e);
        }
    }

}
