package com.consumer.Consumer.PersistenceLayer.mapper;

import com.consumer.Consumer.DomainLayer.DTO.MatchEventPlayerDTOs.MatchEventPlayerDTO;
import com.consumer.Consumer.PersistenceLayer.jpa.MatchEventPlayerJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Mapper(componentModel = "spring")
public interface IMatchEventPlayerMapper {

    IMatchEventPlayerMapper INSTANCE = Mappers.getMapper(IMatchEventPlayerMapper.class);

    /**
     * Converts MatchEventPlayerDTO to MatchEventPlayerJPA entity
     * @param dto the DTO to convert
     * @return the JPA entity
     */
    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "stringToLocalDateTime")
    MatchEventPlayerJPA map(MatchEventPlayerDTO dto);

    /**
     * Converts MatchEventPlayerJPA entity to MatchEventPlayerDTO
     * @param entity the JPA entity to convert
     * @return the DTO
     */
    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "localDateTimeToString")
    MatchEventPlayerDTO toDTO(MatchEventPlayerJPA entity);

    /**
     * Converts string timestamp to LocalDateTime
     * @param timestampStr the timestamp string
     * @return LocalDateTime
     */
    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String timestampStr) {
        if (timestampStr == null || timestampStr.trim().isEmpty()) {
            return LocalDateTime.now();
        }

        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        };

        // Try parsing with different formatters
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(timestampStr, formatter);
            } catch (DateTimeParseException e) {
                // Continue to next formatter
            }
        }

        // Try parsing as epoch milliseconds
        try {
            long epochMilli = Long.parseLong(timestampStr);
            return LocalDateTime.ofEpochSecond(epochMilli / 1000, 0, java.time.ZoneOffset.UTC);
        } catch (NumberFormatException e) {
            return LocalDateTime.now();
        }
    }

    /**
     * Converts LocalDateTime to string timestamp
     * @param timestamp the LocalDateTime
     * @return formatted timestamp string
     */
    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}