package com.consumer.Consumer.asyncMessagingLayer;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class CustomJsonDeserializer implements Deserializer<MatchCommentDTO> {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomJsonDeserializer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configuration if needed
    }
    
    @Override
    public MatchCommentDTO deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        
        try {
            // First try to deserialize as JSON without type information
            String jsonString = new String(data);
            logger.debug("Attempting to deserialize JSON: {}", jsonString);
            
            // Remove any type information from the JSON if present
            if (jsonString.contains("@class")) {
                // Remove the @class field to avoid class loading issues
                jsonString = jsonString.replaceAll("\"@class\"\\s*:\\s*\"[^\"]*\"\\s*,?", "");
                logger.debug("Removed @class field, JSON: {}", jsonString);
            }
            
            // Also remove any trailing commas that might be left after removing @class
            jsonString = jsonString.replaceAll(",\\s*}", "}");
            jsonString = jsonString.replaceAll(",\\s*]", "]");
            
            logger.debug("Final JSON to deserialize: {}", jsonString);
            
            return objectMapper.readValue(jsonString, MatchCommentDTO.class);
        } catch (IOException e) {
            logger.error("Error deserializing message: {}", e.getMessage(), e);
            // Instead of throwing an exception, return null to skip the message
            logger.warn("Skipping message due to deserialization error");
            return null;
        } catch (Exception e) {
            logger.error("Unexpected error during deserialization: {}", e.getMessage(), e);
            return null;
        }
    }
    
    @Override
    public void close() {
        // Cleanup if needed
    }
}
