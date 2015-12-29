package io.pivotal.cf.cassandra.demo.repositories;

import io.pivotal.cf.cassandra.demo.models.TickerHistory;
import org.springframework.data.repository.CrudRepository;

public interface TickerHistoryRepository extends CrudRepository<TickerHistory, String> {
}
