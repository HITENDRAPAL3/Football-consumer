package com.consumer.Consumer.DomainLayer.DTO.MatchEventPlayerDTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MatchEventPlayerDTO {

    private String matchId;
    private String eventId;
    private String timestamp;
    private Integer minute;
    private String eventType;
    private TeamDTO team;
    private PlayerDTO player;
    private String description;
    private AdditionalInfoDTO additionalInfo;

}