package com.consumer.Consumer.ServiceLayer.logic;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import com.consumer.Consumer.PersistenceLayer.Couchbase.CommentRepository;
import com.consumer.Consumer.PersistenceLayer.mapper.IMatchCommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class PushCommentsToCBStep {

    @Inject
    private CommentRepository commentRepository;

    @Inject
    private IMatchCommentMapper MatchCommentMapper;

    Logger logger = LoggerFactory.getLogger(PushCommentsToCBStep.class);

    public void pushCommentsToCBStep(MatchCommentDTO matchCommentDTO) {
        try {
            commentRepository.save(MatchCommentMapper.map(matchCommentDTO));
            logger.info("Comment saved successfully!!!");
        } catch (Exception e) {
            logger.error("Failed to persist on couchbase!!!");
        }
    }

}
