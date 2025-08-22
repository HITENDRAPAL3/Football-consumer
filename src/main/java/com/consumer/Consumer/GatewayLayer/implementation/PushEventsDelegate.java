package com.consumer.Consumer.GatewayLayer.implementation;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import com.consumer.Consumer.GatewayLayer.implementaion.IPushEventsDelegate;
import com.consumer.Consumer.ServiceLayer.logic.PushCommentsToCBStep;

import javax.inject.Inject;

public class PushEventsDelegate implements IPushEventsDelegate {

    @Inject
    private PushCommentsToCBStep pushCommentsToCBStep;

    @Override
    public void pushEvents(MatchCommentDTO event) {
        pushCommentsToCBStep.pushCommentsToCBStep(event);
    }
}
