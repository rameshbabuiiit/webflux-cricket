package com.learnmodeon.repository;

import com.learnmodeon.model.Player;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface PlayerRepository extends ReactiveCassandraRepository<Player, String> {
}
