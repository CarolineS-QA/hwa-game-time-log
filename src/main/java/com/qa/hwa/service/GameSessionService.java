package com.qa.hwa.service;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.dto.GameSessionDTO;
import com.qa.hwa.repo.GameSessionsRepository;
import com.qa.hwa.repo.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameSessionService {

    private final GameSessionsRepository sessionsRepo;

    private final UsersRepository usersRepo;

    private final ModelMapper mapper;

    @Autowired
    public GameSessionService(GameSessionsRepository repo, UsersRepository usersRepo, ModelMapper mapper) {
        this.sessionsRepo = repo;
        this.usersRepo = usersRepo;
        this.mapper = mapper;
    }

    private GameSessionDTO mapToDTO(GameSession session){
        return this.mapper.map(session, GameSessionDTO.class);
    }



    public GameSessionDTO createGameSession(GameSession session){
        GameSession tempGameSession = this.sessionsRepo.save(session);
        return this.mapToDTO(tempGameSession);
    }
}
