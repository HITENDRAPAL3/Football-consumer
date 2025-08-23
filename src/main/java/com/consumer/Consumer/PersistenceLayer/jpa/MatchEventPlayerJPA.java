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

    @Field("teamId")
    private String teamId;

    @Field("teamName")
    private String teamName;

    @Field("playerId")
    private String playerId;

    @Field("playerName")
    private String playerName;

    @Field("playerPosition")
    private String playerPosition;

    @Field("description")
    private String description;

    @Field("substitutionFor")
    private String substitutionFor;

    @Field("substitutionType")
    private String substitutionType;

}
