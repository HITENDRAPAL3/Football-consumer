package com.consumer.Consumer.PersistenceLayer.Couchbase.matchcomment;

import com.consumer.Consumer.PersistenceLayer.jpa.MatchCommentJPA;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "matchComments", viewName = "all")
public interface CommentRepositoryForMatchComment extends CouchbaseRepository<MatchCommentJPA, Integer> {
}