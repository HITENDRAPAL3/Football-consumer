package com.consumer.Consumer.GatewayLayer.implementaion;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;

public interface IPushEventsDelegate {

    void pushEvents(MatchCommentDTO event);

}
