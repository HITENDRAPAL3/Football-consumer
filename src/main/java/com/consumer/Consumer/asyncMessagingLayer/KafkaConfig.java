package com.consumer.Consumer.asyncMessagingLayer;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import com.consumer.Consumer.DomainLayer.DTO.MatchEventPlayerDTOs.MatchEventPlayerDTO;
import com.consumer.Consumer.common.AppConstants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, MatchCommentDTO> consumerFactoryForMatchComments() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, AppConstants.groupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs,com.football.football.DomainLayer.DTO.MatchCommentDTOs");
        configProps.put(JsonDeserializer.TYPE_MAPPINGS, "com.football.football.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO:com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO");
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MatchCommentDTO.class.getName());
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        configProps.put(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS, true);

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
                new CustomJsonDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MatchCommentDTO> kafkaListenerContainerFactoryForMatchComments() {
        ConcurrentKafkaListenerContainerFactory<String, MatchCommentDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryForMatchComments());

        // Configure error handling for deserialization errors
        factory.setCommonErrorHandler(new org.springframework.kafka.listener.DefaultErrorHandler() {
            @Override
            public void handleOtherException(Exception thrownException, org.apache.kafka.clients.consumer.Consumer<?, ?> consumer,
                                             org.springframework.kafka.listener.MessageListenerContainer container, boolean batchListener) {
                if (thrownException instanceof org.apache.kafka.common.errors.RecordDeserializationException) {
                    // Log the error and continue processing
                    System.err.println("Deserialization error: " + thrownException.getMessage());
                    // You can add custom logic here to handle the error
                } else {
                    super.handleOtherException(thrownException, consumer, container, batchListener);
                }
            }
        });

        return factory;
    }

    @Bean
    public ConsumerFactory<String, MatchEventPlayerDTO> consumerFactoryForMatchEvents() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, AppConstants.groupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.consumer.Consumer.DomainLayer.DTO.MatchEventPlayerDTOs,com.football.football.DomainLayer.DTO.MatchEventPlayerDTOs");
        configProps.put(JsonDeserializer.TYPE_MAPPINGS, "com.football.football.DomainLayer.DTO.MatchEventPlayerDTOs.MatchEventPlayerDTO:com.consumer.Consumer.DomainLayer.DTO.MatchEventPlayerDTOs.MatchEventPlayerDTO");
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MatchEventPlayerDTO.class.getName());
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        configProps.put(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS, true);

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
                new CustomMatchEventPlayerDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MatchEventPlayerDTO> kafkaListenerContainerFactoryForMatchEvents() {
        ConcurrentKafkaListenerContainerFactory<String, MatchEventPlayerDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryForMatchEvents());

        // Configure error handling for deserialization errors
        factory.setCommonErrorHandler(new org.springframework.kafka.listener.DefaultErrorHandler() {
            @Override
            public void handleOtherException(Exception thrownException, org.apache.kafka.clients.consumer.Consumer<?, ?> consumer,
                                             org.springframework.kafka.listener.MessageListenerContainer container, boolean batchListener) {
                if (thrownException instanceof org.apache.kafka.common.errors.RecordDeserializationException) {
                    // Log the error and continue processing
                    System.err.println("Deserialization error: " + thrownException.getMessage());
                    // You can add custom logic here to handle the error
                } else {
                    super.handleOtherException(thrownException, consumer, container, batchListener);
                }
            }
        });

        return factory;
    }
}