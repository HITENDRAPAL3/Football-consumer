package com.consumer.Consumer.PersistenceLayer.Couchbase;

import com.consumer.Consumer.PersistenceLayer.jpa.MatchCommentJPA;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

@ViewIndexed(designDoc = "matchComments", viewName = "all")
public interface CommentRepository extends CouchbaseRepository<MatchCommentJPA, Integer> {
}
