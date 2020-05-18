package com.qa.hwa.rest;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.domain.User;
import com.qa.hwa.dto.GameSessionDTO;
import com.qa.hwa.dto.UserDTO;
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

    @GetMapping("/getAllGameSessions")
    public ResponseEntity<List<GameSessionDTO>> getAllGameSessions(){
        return ResponseEntity.ok(this.service.readAllSessions());
    }

    @GetMapping("/getYourGameSessions/{username}")
    public ResponseEntity<List<GameSessionDTO>> getAUsersGameSessions(@PathVariable String username){
        return ResponseEntity.ok(this.service.readAUsersGameSessions(username));
    }

    @PostMapping("/createGameSession")
    public ResponseEntity<GameSessionDTO> createGameSession(@RequestBody GameSession session){
        return new ResponseEntity<>(this.service.createGameSession(session), HttpStatus.CREATED);
    }

    @PutMapping("/updateGameSession/{id}")
    public ResponseEntity<GameSessionDTO> updateGameSession(@PathVariable Long id, @RequestBody GameSession session){
        return ResponseEntity.ok(this.service.updateGameSession(id, session));
    }

    @DeleteMapping("/deleteGameSession/{id}")
    public ResponseEntity<?> deleteGameSession(@PathVariable Long id){
        return this.service.deleteGameSession(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }
}
