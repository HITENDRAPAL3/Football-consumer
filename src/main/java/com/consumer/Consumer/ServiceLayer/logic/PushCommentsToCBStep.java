package com.consumer.Consumer.ServiceLayer.logic;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import com.consumer.Consumer.DomainLayer.DTO.MatchEventPlayerDTOs.MatchEventPlayerDTO;
import com.consumer.Consumer.PersistenceLayer.Couchbase.CommentRepositoryForMatchComment;
import com.consumer.Consumer.PersistenceLayer.Couchbase.CommentRepositoryForMatchEventPlayer;
import com.consumer.Consumer.PersistenceLayer.mapper.IMatchCommentMapper;
import com.consumer.Consumer.PersistenceLayer.mapper.IMatchEventPlayerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class PushCommentsToCBStep {

    @Inject
    private CommentRepositoryForMatchComment commentRepositoryForMatchComment;

    @Inject
    private CommentRepositoryForMatchEventPlayer commentRepositoryForMatchEventPlayer;

    @Inject
    private IMatchCommentMapper MatchCommentMapper;

    @Inject
    private IMatchEventPlayerMapper matchEventPlayerMapper;

    Logger logger = LoggerFactory.getLogger(PushCommentsToCBStep.class);

    public void pushCommentsToCB(MatchCommentDTO matchCommentDTO) {
        try {
            commentRepositoryForMatchComment.save(MatchCommentMapper.map(matchCommentDTO));
            logger.info("Comment saved successfully!!!");
        } catch (Exception e) {
            logger.error("Failed to persist on couchbase!!!");
        }
    }

    public void pushCommentsToCB(MatchEventPlayerDTO matchEventPlayerDTO) {
        try {
            commentRepositoryForMatchEventPlayer.save(matchEventPlayerMapper.map(matchEventPlayerDTO));
            logger.info("Event details saved successfully!!!");
        } catch (Exception e) {
            logger.error("Failed to persist on couchbase!!!");
        }
    }

}
