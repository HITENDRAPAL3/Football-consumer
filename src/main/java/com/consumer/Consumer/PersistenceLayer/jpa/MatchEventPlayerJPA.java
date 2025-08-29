package com.consumer.Consumer.PersistenceLayer.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MatchEventPlayerJPA {

    @Id
    @Field("eventId")
    private String eventId;

    @Field("matchId")
    private String matchId;

    @Field("timestamp")
    private LocalDateTime timestamp;

    @Field("minute")
    private Integer minute;

    @Field("eventType")
    private String eventType;

    @Field("team")
    private String team;

    @Field("player")
    private String player;

    @Field("description")
    private String description;

    @Field("additionalInfo")
    private String additionalInfo;

}
