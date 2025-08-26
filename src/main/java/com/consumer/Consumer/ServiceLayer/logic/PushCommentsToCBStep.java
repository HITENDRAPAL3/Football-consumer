package com.consumer.Consumer.ServiceLayer.logic;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import com.consumer.Consumer.DomainLayer.DTO.MatchEventPlayerDTOs.MatchEventPlayerDTO;
import com.consumer.Consumer.PersistenceLayer.Couchbase.matchcomment.CommentRepositoryForMatchComment;
import com.consumer.Consumer.PersistenceLayer.Couchbase.matchevent.CommentRepositoryForMatchEventPlayer;
import com.consumer.Consumer.PersistenceLayer.mapper.IMatchCommentMapper;
import com.consumer.Consumer.PersistenceLayer.mapper.IMatchEventPlayerMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;

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

    @Inject
    private SimpMessagingTemplate simpMessagingTemplate;

    Logger logger = LoggerFactory.getLogger(PushCommentsToCBStep.class);

    public void pushCommentsToCB(MatchCommentDTO matchCommentDTO) {
        try {
            commentRepositoryForMatchComment.save(MatchCommentMapper.map(matchCommentDTO));
            logger.info("Comment saved successfully!!!");
            broadcastCommentsToUsers(matchCommentDTO);
        } catch (Exception e) {
            logger.error("Failed to persist on couchbase!!!");
        }
    }

    public void pushEventsForMatchEvents(MatchEventPlayerDTO matchEventPlayerDTO) {
        try {
            commentRepositoryForMatchEventPlayer.save(matchEventPlayerMapper.map(matchEventPlayerDTO));
            logger.info("Event details saved successfully!!!");
            broadcastMatchEventsToUsers(matchEventPlayerDTO);
        } catch (Exception e) {
            logger.error("Failed to persist on couchbase!!!");
        }
    }

    private void broadcastCommentsToUsers(MatchCommentDTO matchCommentDTO) {
        simpMessagingTemplate.convertAndSend("/topic/matchComments", matchCommentDTO);
    }

    private void broadcastMatchEventsToUsers(MatchEventPlayerDTO matchEventPlayerDTO) {
        simpMessagingTemplate.convertAndSend("/topic/matchEvents", matchEventPlayerDTO);
    }

}
