package com.consumer.Consumer.GatewayLayer.interfaces;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import com.consumer.Consumer.DomainLayer.DTO.MatchEventPlayerDTOs.MatchEventPlayerDTO;

public interface IPushEventsDelegate {

    void pushEventsForMatchComments(MatchCommentDTO event);
    void pushEventsForMatchEvents(MatchEventPlayerDTO event);

}
