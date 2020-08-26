package com.qa.hwa.rest;

import com.qa.hwa.domain.GameSession;
import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.exceptions.UserNotFoundException;
import com.qa.hwa.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

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
    private List<GameSession> sessionsList;
    private String username;

    private final ModelMapper mapper = new ModelMapper();

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp(){
        sessionsList = new ArrayList<>();
        this.usersList = new ArrayList<>();
        this.testUser = new User("testUser", time, time, time, sessionsList);
        username = testUser.getUsername();
        this.usersList.add(testUser);
        this.testUserWithId = new User(testUser.getUsername(), testUser.getTotalTimePlayed(), testUser.getFreeTime(), testUser.getTimeRemaining(), testUser.getGameSessions());
        this.testUserWithId.setUserId(this.userId);
        this.userDTO = this.mapToDTO(testUserWithId);
    }

    @Test
    public void getAllUsersTest() {
        when(service.readAllUsers()).thenReturn(this.usersList.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No users found", Objects.requireNonNull(this.userController.getAllUsers().getBody()).isEmpty());
        verify(service, times(1)).readAllUsers();
    }

    @Test
    public void getUserByUsernameTest() throws UserNotFoundException {
        when(this.service.readUserByUsername(username)).thenReturn(this.userDTO);
        assertEquals(this.userController.getUserByUsername(username), new ResponseEntity<>(this.userDTO, HttpStatus.OK));
        verify(service, times(1)).readUserByUsername(username);
    }

    @Test
    public void createUserTest() {
        when(this.service.createUser(testUser)).thenReturn(this.userDTO);
        assertEquals(this.userController.createUser(testUser), new ResponseEntity<>(this.userDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createUser(testUser);
    }

    @Test
    public void getUserByIdTest() throws UserNotFoundException {
        when(this.service.findUserById(userId)).thenReturn(this.userDTO);
        assertEquals(this.userController.getUserById(userId), new ResponseEntity<>(this.userDTO, HttpStatus.OK));
        verify(service, times(1)).findUserById(userId);
    }

    @Test
    public void updateUserTest() throws UserNotFoundException {
        when(this.service.updateUser(userId, testUser)).thenReturn(this.userDTO);
        assertEquals(this.userController.updateUser(userId, testUser), new ResponseEntity<>(this.userDTO, HttpStatus.OK));
        verify(service, times(1)).updateUser(userId, testUser);
    }

    @Test
    public void deleteUserTestFalse() throws UserNotFoundException {
        this.userController.deleteUser(userId);
        verify(service, times(1)).deleteUser(userId);
    }


    @Test
    public void deleteUserTestTrue() throws UserNotFoundException {
        when(service.deleteUser(3L)).thenReturn(true);
        this.userController.deleteUser(3L);
        verify(service, times(1)).deleteUser(3L);
    }
}

