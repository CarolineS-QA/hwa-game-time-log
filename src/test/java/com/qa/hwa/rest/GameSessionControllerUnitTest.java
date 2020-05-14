package com.qa.hwa.rest;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.domain.User;
import com.qa.hwa.dto.GameSessionDTO;
import com.qa.hwa.service.GameSessionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameSessionControllerUnitTest {
    @InjectMocks
    private GameSessionController sessionController;

    @Mock
    private GameSessionService service;

    private User player1;
    private List<GameSession> gameSessionsList;
    private List<GameSessionDTO> gameSessionDTOList;
    private GameSession testSession;
    private Long sessionId = 1L;
    private GameSession testSessionWithId;
    private GameSessionDTO sessionDTO;
    private Duration zeroTime;
    private LocalDateTime date;

    private final ModelMapper mapper = new ModelMapper();

    private GameSessionDTO mapToDTO(GameSession session){
        return this.mapper.map(session, GameSessionDTO.class);
    }

    @Before
    public void setUp(){
        gameSessionsList = new ArrayList<>();
        gameSessionDTOList = new ArrayList<>();
        this.player1 = new User("testUser", zeroTime, zeroTime, zeroTime, null);
        this.testSession = new GameSession(player1, "hello world", zeroTime, date);
        this.gameSessionsList.add(testSession);
        this.testSessionWithId = new GameSession(testSession.getUser(), testSession.getGameName(), testSession.getTimePlayed(), testSession.getTimeOfSession());
        this.testSessionWithId.setSessionId(sessionId);
        this.sessionDTO = this.mapToDTO(testSessionWithId);
        gameSessionDTOList.add(sessionDTO);
    }

    @Test
    public void getAllGameSessionsTest(){
        when(this.service.readAllSessions()).thenReturn(this.gameSessionDTOList);
        assertEquals(this.sessionController.getAllGameSessions(), new ResponseEntity<>(this.gameSessionDTOList, HttpStatus.OK));
        verify(this.service, times(1)).readAllSessions();
    }

    @Test
    public void getAUsersGameSessionsTest(){
        when(this.service.readAUsersGameSessions(player1.getUsername())).thenReturn(this.gameSessionDTOList);
        assertEquals(this.sessionController.getAUsersGameSessions(player1.getUsername()), new ResponseEntity<>(this.gameSessionDTOList, HttpStatus.OK));
        verify(this.service, times(1)).readAUsersGameSessions(player1.getUsername());
    }

    @Test
    public void createGameSessionTest() {
        when(this.service.createGameSession(testSession)).thenReturn(this.sessionDTO);
        assertEquals(this.sessionController.createGameSession(testSession), new ResponseEntity<>(this.sessionDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createGameSession(testSession);
    }

    @Test
    public void updateGameSessionTest(){
        when(this.service.updateGameSession(sessionId, testSession)).thenReturn(this.sessionDTO);
        assertEquals(this.sessionController.updateGameSession(sessionId, testSession), new ResponseEntity<>(this.sessionDTO, HttpStatus.OK));
        verify(service, times(1)).updateGameSession(sessionId, testSession);
    }

    @Test
    public void deleteUserTestFalse(){
        this.sessionController.deleteGameSession(sessionId);
        verify(service, times(1)).deleteGameSession(sessionId);
    }

    @Test
    public void deleteUserTestTrue(){
        when(service.deleteGameSession(3L)).thenReturn(true);
        this.sessionController.deleteGameSession(3L);
        verify(service, times(1)).deleteGameSession(3L);
    }
}
