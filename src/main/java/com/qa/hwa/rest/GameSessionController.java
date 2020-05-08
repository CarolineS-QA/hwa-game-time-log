package com.qa.hwa.rest;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.domain.User;
import com.qa.hwa.dto.GameSessionDTO;
import com.qa.hwa.service.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameSessionController {

    private final GameSessionService service;

    @Autowired
    public GameSessionController(GameSessionService service) {
        this.service = service;
    }

    @GetMapping("/readAllGameSessionsByTime")
    public ResponseEntity<List<GameSessionDTO>> readAllGameSessionsOrderedByTime(){
        return ResponseEntity.ok(this.service.readAllSessionsByTimeOfSession());
    }

    @GetMapping("/readYourGameSessions/{user.username}")
    public ResponseEntity<List<GameSessionDTO>> readAUsersGameSessions(@PathVariable User user){
        return ResponseEntity.ok(this.service.readAUsersGameSessions(user));
    }

    @PostMapping("/createGameSession")
    public ResponseEntity<GameSessionDTO> createGameSession(@RequestBody GameSession session){
        return new ResponseEntity<>(this.service.createGameSession(session), HttpStatus.CREATED);
    }
}
