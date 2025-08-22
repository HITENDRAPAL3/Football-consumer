package com.consumer.Consumer.GatewayLayer.implementation;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;

public interface IPushEventsDelegate {

    void pushEvents(MatchCommentDTO event);

}
