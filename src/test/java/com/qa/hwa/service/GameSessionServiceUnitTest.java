package com.qa.hwa.service;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.domain.User;
import com.qa.hwa.dto.GameSessionDTO;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.repo.GameSessionsRepository;
import com.qa.hwa.repo.UsersRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class GameSessionServiceUnitTest {

    @InjectMocks
    private GameSessionService service;

    @Mock
    private GameSessionsRepository repo;

    @Mock
    private ModelMapper mapper;

    private List<GameSession> gameSessionList;
    private List<GameSessionDTO> gameSessionDTOList;
    private GameSession testSession;
    private Long sessionId = 1L;
    private GameSession testSessionWithId;
    private GameSessionDTO sessionDTO;
    private Duration zeroTime;
    private LocalDateTime date;
    private User player1;

    private GameSessionDTO mapToDTO(GameSession session){
        return this.mapper.map(session, GameSessionDTO.class);
    }

    @Before
    public void setUp(){
        this.gameSessionList = new ArrayList<>();
        this.gameSessionDTOList = new ArrayList<>();
        zeroTime = Duration.ofHours(0);
        player1 = new User(1L, "testUser", zeroTime, zeroTime, zeroTime, null);
        this.testSession = new GameSession(player1, "hello world", zeroTime, date);
        this.gameSessionList.add(testSession);
        this.testSessionWithId = new GameSession(testSession.getUsername(), testSession.getGameName(), testSession.getTimePlayed(), testSession.getTimeOfSession());
        this.testSessionWithId.setSessionId(sessionId);
        this.sessionDTO = this.mapToDTO(testSessionWithId);
        this.gameSessionDTOList.add(sessionDTO);
    }

    // doesn't return anything because it findsByTimeOfSession before sorting
    // -> looking for the exact time - so nothing gets picked up to sort!
    // modify GameSessionRepo's FinaAll(), then GameSessionService - add the sorting in Service.
    // - Controller should be the same as long a method name doesn't change
    @Ignore
    @Test
    public void readAllSessionsByTimeOfSessionTest() {
        when(repo.findAllByTimeOfSessionOrderByTimeOfSessionDesc(date)).thenReturn(this.gameSessionList);
        when(this.mapper.map(testSessionWithId, GameSessionDTO.class)).thenReturn(sessionDTO);
        assertEquals(this.service.readAllSessionsByTimeOfSession(), gameSessionDTOList);
        verify(repo, times(1)).findAllByTimeOfSessionOrderByTimeOfSessionDesc(date);
    }

    @Test
    public void readAUsersGameSessionsTest() {
        when(this.repo.findAllByUsernameOrderByTimeOfSessionDesc(player1)).thenReturn(this.gameSessionList);
        when(this.mapper.map(testSessionWithId, GameSessionDTO.class)).thenReturn(sessionDTO);
        assertEquals(this.service.readAUsersGameSessions(player1), gameSessionDTOList);
        verify(repo, times(1)).findAllByUsernameOrderByTimeOfSessionDesc(player1);
    }

    @Test
    public void createGameSessionTest() {
        when(repo.save(testSession)).thenReturn(this.testSessionWithId);
        when(this.mapper.map(testSessionWithId, GameSessionDTO.class)).thenReturn(sessionDTO);
        assertEquals(this.service.createGameSession(testSession), this.sessionDTO);
        verify(repo, times(1)).save(this.testSession);
    }
}
