package com.consumer.Consumer.ServiceLayer.logic;

import com.consumer.Consumer.DomainLayer.DTO.MatchCommentDTOs.MatchCommentDTO;
import com.consumer.Consumer.PersistenceLayer.Couchbase.CommentRepository;
import com.consumer.Consumer.PersistenceLayer.mapper.IMatchCommentMapper;

import javax.inject.Inject;

public class PushCommentsToCBStep {

    @Inject
    private CommentRepository commentRepository;

    @Inject
    private IMatchCommentMapper MatchCommentMapper;

    public void pushCommentsToCBStep(MatchCommentDTO matchCommentDTO) {
        commentRepository.save(MatchCommentMapper.map(matchCommentDTO));
    }

}
