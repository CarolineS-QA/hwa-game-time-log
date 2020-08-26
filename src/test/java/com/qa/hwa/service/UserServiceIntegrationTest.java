package com.qa.hwa.service;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.exceptions.UserNotFoundException;
import com.qa.hwa.repo.UsersRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService service;

    @Autowired
    private UsersRepository repo;

    @Autowired
    private ModelMapper mapper;

    private User testUser;
    private User testUserWithId;
    private Duration zeroTime;
    private List<GameSession> sessionsList;

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp(){
        sessionsList = new ArrayList<>();
        zeroTime = Duration.ofHours(0);
        this.testUser = new User("testUser", zeroTime, zeroTime, zeroTime, sessionsList);
        this.repo.deleteAll();
        this.testUserWithId = this.repo.save(this.testUser);
    }
    @Ignore
    @Test
    public void readAllUsersTest(){
        assertThat(this.service.readAllUsers()).isEqualTo(
                Stream.of(this.mapToDTO(testUserWithId)).collect(Collectors.toList())
        );
    }
    @Ignore
    @Test
    public void createUserTest(){
        assertEquals(this.mapToDTO(this.testUserWithId), this.service.createUser(testUser));
    }
    @Ignore
    @Test
    public void findUserByIdTest() throws UserNotFoundException {
        assertThat(this.service.findUserById(this.testUserWithId.getUserId())).isEqualTo(this.mapToDTO(this.testUserWithId));
    }
    @Ignore
    @Test
    public void updateUserTest() throws UserNotFoundException {
        User newUser = new User("newTestUser", zeroTime, zeroTime, zeroTime, sessionsList);
        User updateUser = new User(newUser.getUsername(), newUser.getTotalTimePlayed(), newUser.getFreeTime(), newUser.getTimeRemaining(), newUser.getGameSessions());
        updateUser.setUserId(testUserWithId.getUserId());
        assertThat(this.service.updateUser(this.testUserWithId.getUserId(),newUser))
                .isEqualTo(this.mapToDTO(updateUser));
    }

    @Test
    public void deleteUserTest() throws UserNotFoundException {
        assertThat(this.service.deleteUser(this.testUserWithId.getUserId())).isFalse();
    }
}
