package com.qa.hwa.service;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.domain.User;
import com.qa.hwa.dto.GameSessionDTO;
import com.qa.hwa.exceptions.GameSessionNotFoundException;
import com.qa.hwa.repo.GameSessionsRepository;
import com.qa.hwa.repo.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<GameSessionDTO> readAllSessions/*OrderedByTimeOfSession*/(){
        return this.sessionsRepo.findAll(/*Sort.by(Sort.Direction.DESC, "TIME_OF_SESSION")*/).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public User readUserByUsername(String username){
        return this.usersRepo.findUserByUsername(username);
    }

    public List<GameSessionDTO> readAUsersGameSessions(String username){
        User user = readUserByUsername(username);
        return this.sessionsRepo.findAllByUser(user).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public GameSessionDTO createGameSession(GameSession session){
        GameSession tempGameSession = this.sessionsRepo.save(session);
        return this.mapToDTO(tempGameSession);
    }

    //Restricting the Update. So a session can't be reassigned to different user.
    public GameSessionDTO updateGameSession(Long id, @NotNull GameSession session){
        GameSession update = this.sessionsRepo.findById(id).orElseThrow(GameSessionNotFoundException::new);
        update.setGameName(session.getGameName());
        update.setTimeOfSession(session.getTimeOfSession()); //could set this to LocalDateTime.now()
        update.setTimePlayed(session.getTimePlayed());
        //update.setUsername(session.getUsername());
        GameSession tempGameSession = this.sessionsRepo.save(update);
        return this.mapToDTO(tempGameSession);
    }

    public boolean deleteGameSession(Long id){
        if(!this.sessionsRepo.existsById(id)){
            throw new GameSessionNotFoundException();
        }
        this.sessionsRepo.deleteById(id);
        return this.sessionsRepo.existsById(id);
    }
}
