package com.qa.hwa.rest;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.dto.GameSessionDTO;
import com.qa.hwa.service.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameSessionController {

    private final GameSessionService service;

    @Autowired
    public GameSessionController(GameSessionService service) {
        this.service = service;
    }



    @PostMapping("/createGameSession")
    public ResponseEntity<GameSessionDTO> createGameSession(@RequestBody GameSession session){
        return new ResponseEntity<>(this.service.createGameSession(session), HttpStatus.CREATED);
    }
}
