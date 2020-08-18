package com.qa.hwa.repo;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameSessionsRepository extends MongoRepository<GameSession, Long> {
    List<GameSession> findAllByUser(User username);
    List<GameSession> findAll(Sort sort);
}
