package com.consumer.Consumer.GatewayLayer.implementation;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import com.consumer.Consumer.ServiceLayer.logic.PushCommentsToCBStep;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PushEventsDelegate implements IPushEventsDelegate {

    @Inject
    private PushCommentsToCBStep pushCommentsToCBStep;

    @Override
    public void pushEvents(MatchCommentDTO event) {
        pushCommentsToCBStep.pushCommentsToCBStep(event);
    }
}
