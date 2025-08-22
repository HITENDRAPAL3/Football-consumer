package com.consumer.Consumer.MatchCommentDTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MatchCommentDTO {

    private String commentId;
    private String matchId;
    private String userId;
    private String userName;
    private String text;
    private String timestamp;
    private Integer rating;

}
