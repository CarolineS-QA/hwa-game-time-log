package com.qa.hwa.service;

import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.repo.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
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

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp(){
        zeroTime = Duration.ofHours(0);
        this.testUser = new User("testUser", zeroTime, zeroTime, zeroTime);
        this.repo.deleteAll();
        this.testUserWithId = this.repo.save(this.testUser);
    }

    @Test
    public void readAllUsersTest(){
        assertThat(this.service.readAllUsers()).isEqualTo(
                Stream.of(this.mapToDTO(testUserWithId)).collect(Collectors.toList())
        );
    }

    @Test
    public void createUserTest(){
        assertEquals(this.mapToDTO(this.testUserWithId), this.service.createUser(testUser));
    }

    @Test
    public void findUserByIdTest(){
        assertThat(this.service.findUserById(this.testUserWithId.getUserId())).isEqualTo(this.mapToDTO(this.testUserWithId));
    }

    @Test
    public void updateUserTest(){
        User newUser = new User("newTestUser", zeroTime, zeroTime, zeroTime);
        User updateUser = new User(newUser.getUsername(), newUser.getTotalTimePlayed(), newUser.getFreeTime(), newUser.getTimeRemaining());
        updateUser.setUserId(testUserWithId.getUserId());
        assertThat(this.service.updateUser(this.testUserWithId.getUserId(),newUser))
                .isEqualTo(this.mapToDTO(updateUser));
    }

    @Test
    public void deleteUserTest(){
        assertThat(this.service.deleteUser(this.testUserWithId.getUserId())).isFalse();
    }
}
