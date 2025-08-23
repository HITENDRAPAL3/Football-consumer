package com.consumer.Consumer.PersistenceLayer.Couchbase.matchevent;

import com.consumer.Consumer.PersistenceLayer.jpa.MatchEventPlayerJPA;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "matchEvents", viewName = "all")
public interface CommentRepositoryForMatchEventPlayer extends CouchbaseRepository<MatchEventPlayerJPA, Integer> {
}