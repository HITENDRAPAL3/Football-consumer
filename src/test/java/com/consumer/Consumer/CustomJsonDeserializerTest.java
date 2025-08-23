package com.consumer.Consumer;

import com.consumer.Consumer.asyncMessagingLayer.CustomJsonDeserializer;
import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomJsonDeserializerTest {

    @Test
    public void testDeserializeWithOldClassName() {
        CustomJsonDeserializer deserializer = new CustomJsonDeserializer();
        
        // JSON with the old class name that was causing the error
        String jsonWithOldClass = "{\"@class\":\"com.football.football.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO\",\"commentId\":\"123\",\"matchId\":\"456\",\"userId\":\"789\",\"userName\":\"TestUser\",\"text\":\"Test comment\",\"timestamp\":\"2023-01-01T00:00:00Z\",\"rating\":5}";
        
        byte[] data = jsonWithOldClass.getBytes();
        MatchCommentDTO result = deserializer.deserialize("test-topic", data);
        
        assertNotNull(result);
        assertEquals("123", result.getCommentId());
        assertEquals("456", result.getMatchId());
        assertEquals("789", result.getUserId());
        assertEquals("TestUser", result.getUserName());
        assertEquals("Test comment", result.getText());
        assertEquals("2023-01-01T00:00:00Z", result.getTimestamp());
        assertEquals(5, result.getRating());
    }
    
    @Test
    public void testDeserializeWithoutClassName() {
        CustomJsonDeserializer deserializer = new CustomJsonDeserializer();
        
        // JSON without class name
        String jsonWithoutClass = "{\"commentId\":\"123\",\"matchId\":\"456\",\"userId\":\"789\",\"userName\":\"TestUser\",\"text\":\"Test comment\",\"timestamp\":\"2023-01-01T00:00:00Z\",\"rating\":5}";
        
        byte[] data = jsonWithoutClass.getBytes();
        MatchCommentDTO result = deserializer.deserialize("test-topic", data);
        
        assertNotNull(result);
        assertEquals("123", result.getCommentId());
        assertEquals("456", result.getMatchId());
        assertEquals("789", result.getUserId());
        assertEquals("TestUser", result.getUserName());
        assertEquals("Test comment", result.getText());
        assertEquals("2023-01-01T00:00:00Z", result.getTimestamp());
        assertEquals(5, result.getRating());
    }
    
    @Test
    public void testDeserializeNullData() {
        CustomJsonDeserializer deserializer = new CustomJsonDeserializer();
        MatchCommentDTO result = deserializer.deserialize("test-topic", null);
        assertNull(result);
    }
    
    @Test
    public void testDeserializeInvalidJson() {
        CustomJsonDeserializer deserializer = new CustomJsonDeserializer();
        
        // Invalid JSON
        String invalidJson = "{\"commentId\":\"123\",\"matchId\":\"456\",\"userId\":\"789\",\"userName\":\"TestUser\",\"text\":\"Test comment\",\"timestamp\":\"2023-01-01T00:00:00Z\",\"rating\":5,";
        
        byte[] data = invalidJson.getBytes();
        MatchCommentDTO result = deserializer.deserialize("test-topic", data);
        
        // Should return null instead of throwing exception
        assertNull(result);
    }
}
