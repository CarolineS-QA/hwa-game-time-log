package com.qa.hwa.rest;

import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService service;

    private List<User> usersList;
    private User testUser;
    private User testUserWithId;
    private long userId = 1L;
    private Duration time;
    private UserDTO userDTO;

    private final ModelMapper mapper = new ModelMapper();

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp(){
        this.usersList = new ArrayList<>();
        this.testUser = new User("testUser", time, time, time);
        this.usersList.add(testUser);
        this.testUserWithId = new User(testUser.getUsername(), testUser.getTotalTimePlayed(), testUser.getFreeTime(), testUser.getTimeRemaining());
        this.testUserWithId.setUserId(this.userId);
        this.userDTO = this.mapToDTO(testUserWithId);
    }

    @Test
    public void getAllUsersTest() {
        when(service.readAllUsers()).thenReturn(this.usersList.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No users found", Objects.requireNonNull(this.userController.getAllUsers().getBody()).isEmpty());
        verify(service, Mockito.times(1)).readAllUsers();
    }
}

