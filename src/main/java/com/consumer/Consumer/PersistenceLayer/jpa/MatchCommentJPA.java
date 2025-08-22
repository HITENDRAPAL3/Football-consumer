package com.consumer.Consumer.PersistenceLayer.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MatchCommentJPA {

    @Id
    @Field("commentId")
    private String commentId;

    @Field("matchId")
    private String matchId;

    @Field("userId")
    private String userId;

    @Field("userName")
    private String userName;

    @Field("text")
    private String text;

    @Field("timestamp")
    private LocalDateTime timestamp;

    @Field("rating")
    private Integer rating;
}