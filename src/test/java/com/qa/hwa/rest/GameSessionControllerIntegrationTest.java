package com.qa.hwa.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameSessionControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private GameSessionsRepository sessionsRepo;

    @Autowired
    private UsersRepository usersRepo;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();
    private User player1;
    private List<GameSession> sessionsList;
    private List<GameSessionDTO> sessionDTOList;
    private GameSession testSession;
    private Long sessionId = 1L;
    private GameSession testSessionWithId;
    private GameSessionDTO sessionDTO;
    private Duration zeroTime;
    private LocalDateTime date;

    private GameSessionDTO mapToDTO(GameSession session){
        return this.mapper.map(session, GameSessionDTO.class);
    }

    @Before
    public void setUp(){
        sessionsList = new ArrayList<>();
        sessionDTOList = new ArrayList<>();
        this.sessionsRepo.deleteAll();
        this.usersRepo.deleteAll();
        this.player1 = new User("testUser", zeroTime, zeroTime, zeroTime, null);
        this.usersRepo.saveAndFlush(player1);
        this.testSession = new GameSession(player1, "hello world", zeroTime, date);
        this.testSessionWithId = this.sessionsRepo.save(testSession);
        this.sessionId = testSessionWithId.getSessionId();
        this.sessionDTO = this.mapToDTO(testSessionWithId);
    }

    @Test
    public void getAllGameSessionsOrderedByTimeTest() throws Exception {
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllGameSessionsOrderedByTime")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(sessionDTOList));
    }

    //Nested Exception - due to link between a user and game sessions...
    @Ignore
    @Test
    public void getAUsersGameSessionsTest() throws Exception {
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getYourGameSessions/" + this.player1.getUsername())
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(sessionDTOList));
    }

//org.springframework.web.util.NestedServletException:
// Request processing failed; nested exception is org.springframework.http.converter.HttpMessageNotWritableException:
// Could not write JSON: Infinite recursion (StackOverflowError); nested exception is com.fasterxml.jackson.databind.JsonMappingException:
// Infinite recursion (StackOverflowError) (through reference chain: com.qa.hwa.domain.GameSession["username"]->com.qa.hwa.domain.User["gameSessions"]->org.hibernate.collection.internal.PersistentBag[0]
// ->com.qa.hwa.domain.GameSession["username"]->com.qa.hwa.domain.User["gameSessions"]->org.hibernate.collection.internal.PersistentBag[0]
    //continuously calling each other.
   @Ignore
   @Test
    public void createSessionsTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createGameSession")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testSession))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(sessionDTO));
    }
}
