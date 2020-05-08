package com.qa.hwa.service;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.dto.GameSessionDTO;
import com.qa.hwa.repo.GameSessionsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameSessionService {

    public final GameSessionsRepository repo;

    public final ModelMapper mapper;

    @Autowired
    public GameSessionService(GameSessionsRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private GameSessionDTO mapToDTO(GameSession session){
        return this.mapper.map(session, GameSessionDTO.class);
    }



    public GameSessionDTO createGameSession(GameSession session){
        GameSession tempGameSession = this.repo.save(session);
        return this.mapToDTO(tempGameSession);
    }
}
