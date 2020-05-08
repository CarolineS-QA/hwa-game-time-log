package com.qa.hwa.repo;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GameSessionsRepository extends JpaRepository<GameSession, Long> {
    List<GameSession> findAllByUsernameOrderByTimeOfSessionDesc(User username);
    List<GameSession> findAllByTimeOfSessionOrderByTimeOfSessionDesc(LocalDateTime timeOfSession);
}
