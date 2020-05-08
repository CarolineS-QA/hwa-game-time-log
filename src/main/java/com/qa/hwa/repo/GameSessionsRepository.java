package com.qa.hwa.repo;

import com.qa.hwa.domain.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSessionsRepository extends JpaRepository<GameSession, Long> {
    GameSession findByUsernameOrderByTimeOfSession(String username);
    GameSession findAllBySessionIdOrderByTimeOfSession(Long sessionId);
}
