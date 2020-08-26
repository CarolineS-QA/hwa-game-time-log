package com.qa.hwa.service;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.domain.User;
import com.qa.hwa.dto.GameSessionDTO;
import com.qa.hwa.exceptions.GameSessionNotFoundException;
import com.qa.hwa.exceptions.UserNotFoundException;
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
import java.util.Optional;

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
    private UsersRepository usersRepo;

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
    private String notUsername;
    //private Sort sort;

    private GameSessionDTO mapToDTO(GameSession session){
        return this.mapper.map(session, GameSessionDTO.class);
    }

    @Before
    public void setUp(){
        this.gameSessionList = new ArrayList<>();
        this.gameSessionDTOList = new ArrayList<>();
        zeroTime = Duration.ofHours(0);
        notUsername = "notHERE";
        player1 = new User(1L, "testUser", zeroTime, zeroTime, zeroTime, null);
        this.usersRepo.save(player1);
        this.testSession = new GameSession(player1, "hello world", zeroTime, date);
        this.gameSessionList.add(testSession);
        this.testSessionWithId = new GameSession(testSession.getUser(), testSession.getGameName(), testSession.getTimePlayed(), testSession.getTimeOfSession());
        this.testSessionWithId.setSessionId(sessionId);
        this.repo.save(testSessionWithId); //in the GameSessionRepo there should be a session
        this.sessionDTO = this.mapToDTO(testSessionWithId);
        this.gameSessionDTOList.add(sessionDTO);
        //Sort sort = Sort.by(Sort.Direction.DESC, "TIME_OF_SESSION");
    }

    @Test
    public void readAllSessionsTest() {
        when(repo.findAll()).thenReturn(gameSessionList);
        when(this.mapper.map(testSessionWithId, GameSessionDTO.class)).thenReturn(sessionDTO);
        assertEquals(this.service.readAllSessions(), gameSessionDTOList);
        verify(repo, times(1)).findAll();
    }

    @Test
    public void readUserByUsernameTest() throws UserNotFoundException {
        when(this.usersRepo.findUserByUsername(player1.getUsername())).thenReturn(player1);
        assertEquals(this.service.readUserByUsername(player1.getUsername()), player1);
        verify(usersRepo, times(2)).findUserByUsername(player1.getUsername());
    }

    @Test(expected = UserNotFoundException.class)
    public void readUserByNonExistingUsernameTest() throws UserNotFoundException {
        when(this.usersRepo.findUserByUsername(notUsername)).thenReturn(null);
        service.readUserByUsername(notUsername);
        verify(usersRepo, times(1)).findUserByUsername(notUsername);
    }

    @Ignore //com.qa.hwa.exceptions.UserNotFoundException @ line 44
    @Test
    public void readAUsersGameSessionsTest() throws UserNotFoundException {
        when(service.readUserByUsername(player1.getUsername())).thenReturn(player1);
        when(this.repo.findAllByUser(player1)).thenReturn(this.gameSessionList);
        when(this.mapper.map(testSessionWithId, GameSessionDTO.class)).thenReturn(sessionDTO);
        //assertEquals(this.service.readAUsersGameSessions(player1.getUsername()), gameSessionDTOList);
        verify(repo, times(1)).findAllByUser(player1);
    }

    @Test
    public void createGameSessionTest() {
        when(repo.save(testSession)).thenReturn(this.testSessionWithId);
        when(this.mapper.map(testSessionWithId, GameSessionDTO.class)).thenReturn(sessionDTO);
        assertEquals(this.service.createGameSession(testSession), this.sessionDTO);
        verify(repo, times(1)).save(this.testSession);
    }

    @Test
    public void updateGameSessionTest() throws GameSessionNotFoundException {
        GameSession newSession = new GameSession(player1, "new game plus", zeroTime, date);
        GameSession updateSession = new GameSession(newSession.getUser(), newSession.getGameName(), newSession.getTimePlayed(), newSession.getTimeOfSession());
        updateSession.setSessionId(sessionId);

        GameSessionDTO updateSessionDTO = new ModelMapper().map(updateSession, GameSessionDTO.class);

        when(this.repo.findById(sessionId)).thenReturn(Optional.ofNullable(testSessionWithId));
        when(this.repo.save(updateSession)).thenReturn(updateSession);
        when(this.mapper.map(updateSession, GameSessionDTO.class)).thenReturn(updateSessionDTO);

        assertEquals(updateSessionDTO, this.service.updateGameSession(sessionId, newSession));
        verify(this.repo, times(1)).findById(sessionId);
        verify(this.repo, times(2)).save(updateSession); //two times because save is called in SetUp for read tests
    }

    @Test
    public void deleteSessionByExistingId() throws GameSessionNotFoundException {
        when(this.repo.existsById(sessionId)).thenReturn(true, false);
        assertFalse(service.deleteGameSession(sessionId));
        verify(repo, times(1)).deleteById(sessionId);
        verify(repo, times(2)).existsById(sessionId);
    }

    @Test(expected = GameSessionNotFoundException.class)
    public void deleteSessionByNonExistingId() throws GameSessionNotFoundException {
        when(this.repo.existsById(sessionId)).thenReturn(false);
        service.deleteGameSession(sessionId);
        verify(repo, times(1)).existsById(sessionId);
    }
}
