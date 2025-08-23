# Kafka Deserialization Issue Fix

## Problem Description

The application was encountering a `ClassNotFoundException` when trying to deserialize Kafka messages. The error was:

```
Caused by: java.lang.ClassNotFoundException: com.football.football.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO
```

This occurred because:
1. Kafka messages were serialized with the old class name `com.football.football.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO`
2. The current project uses the new class name `com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO`
3. The default JsonDeserializer was trying to load the old class name directly

## Solution Implemented

### 1. Custom JSON Deserializers

Created two custom deserializers:
- `CustomJsonDeserializer` for `MatchCommentDTO`
- `CustomMatchEventPlayerDeserializer` for `MatchEventPlayerDTO`

These deserializers:
- Remove the `@class` field from JSON messages to avoid class loading issues
- Handle malformed JSON gracefully by returning `null` instead of throwing exceptions
- Log debug information for troubleshooting

### 2. Updated Kafka Configuration

Modified `KafkaConfig.java` to:
- Use custom deserializers instead of the default JsonDeserializer
- Configure proper error handling for deserialization errors
- Set up type mappings for backward compatibility

### 3. Updated Application Properties

Modified `application.properties` to:
- Use the custom deserializer globally
- Set `auto-offset-reset=latest` to skip problematic messages
- Enable debug logging for troubleshooting

### 4. Enhanced Error Handling

Updated `KafkaConsumerService.java` to:
- Handle `null` messages gracefully
- Log warnings when messages are skipped

## Files Modified

1. `src/main/java/com/consumer/Consumer/asyncMessagingLayer/KafkaConfig.java`
2. `src/main/java/com/consumer/Consumer/asyncMessagingLayer/CustomJsonDeserializer.java` (new)
3. `src/main/java/com/consumer/Consumer/asyncMessagingLayer/CustomMatchEventPlayerDeserializer.java` (new)
4. `src/main/java/com/consumer/Consumer/asyncMessagingLayer/KafkaConsumerService.java`
5. `src/main/resources/application.properties`
6. `src/test/java/com/consumer/Consumer/CustomJsonDeserializerTest.java` (new)

## Testing

The solution includes comprehensive tests that verify:
- Deserialization of messages with old class names
- Deserialization of messages without class names
- Handling of null data
- Handling of invalid JSON

Run the tests with:
```bash
./mvnw test -Dtest=CustomJsonDeserializerTest
```

## Benefits

1. **Backward Compatibility**: Can handle messages serialized with old class names
2. **Graceful Error Handling**: Skips problematic messages instead of crashing
3. **Debugging Support**: Comprehensive logging for troubleshooting
4. **Test Coverage**: Automated tests ensure reliability

## Future Considerations

1. **Message Cleanup**: Consider cleaning up old messages in Kafka topics
2. **Monitoring**: Add metrics to track deserialization failures
3. **Alerting**: Set up alerts for high deserialization failure rates
