package com.qa.hwa.service;

import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.exceptions.UserNotFoundException;
import com.qa.hwa.repo.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceUnitTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UsersRepository repo;

    @Mock
    private ModelMapper mapper;

    private List<User> userList;
    private User testUser;
    private Long userId = 1L;
    private User testUserWithId;
    private UserDTO userDTO;
    private Duration time;

    private UserDTO mapToDTO(User user) {
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp() {
        this.userList = new ArrayList<>();
        time = Duration.ofHours(0);
        this.testUser = new User("testUser", time, time, time);
        this.userList.add(testUser);
        this.testUserWithId = new User(testUser.getUsername(), testUser.getTotalTimePlayed(), testUser.getFreeTime(), testUser.getTimeRemaining());
        this.testUserWithId.setUserId(userId);
        this.userDTO = this.mapToDTO(testUserWithId);
    }

    @Test
    public void readAllUsersTest() {
        when(repo.findAll()).thenReturn(this.userList);
        when(this.mapper.map(testUserWithId, UserDTO.class)).thenReturn(userDTO);
        assertFalse("Service returned no Users", this.service.readAllUsers().isEmpty());
        verify(repo, times(1)).findAll();
    }

    @Test
    public void createUserTest() {
        when(repo.save(testUser)).thenReturn(this.testUserWithId);
        when(this.mapper.map(testUserWithId, UserDTO.class)).thenReturn(userDTO);
        assertEquals(this.service.createUser(testUser), this.userDTO);
        verify(repo, times(1)).save(this.testUser);
    }

    @Test
    public void findUserByIdTest() {
        when(this.repo.findById(userId)).thenReturn(java.util.Optional.ofNullable(testUserWithId));
        when(this.mapper.map(testUserWithId, UserDTO.class)).thenReturn(userDTO);
        assertEquals(this.service.findUserById(this.userId), userDTO);
        verify(repo, times(1)).findById(userId);
    }

    @Test
    public void updateUserTest() {
        User newUser = new User("newTestUser", time, time, time);
        User updateUser = new User(newUser.getUsername(), newUser.getTotalTimePlayed(), newUser.getFreeTime(), newUser.getTimeRemaining());
        updateUser.setUserId(userId);

        UserDTO updateNoteDTO = new ModelMapper().map(updateUser, UserDTO.class);

        when(this.repo.findById(userId)).thenReturn(java.util.Optional.ofNullable(testUserWithId));
        when(this.repo.save(updateUser)).thenReturn(updateUser);
        when(this.mapper.map(updateUser, UserDTO.class)).thenReturn(updateNoteDTO);

        assertEquals(updateNoteDTO, this.service.updateUser(userId, newUser));
        verify(this.repo, times(1)).findById(userId);
        verify(this.repo, times(1)).save(updateUser);
    }

    @Test
    public void deleteUserByExistingId(){
        when(this.repo.existsById(userId)).thenReturn(true, false);
        assertFalse(service.deleteUser(userId));
        verify(repo, times(1)).deleteById(userId);
        verify(repo, times(2)).existsById(userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteUserByNonExistingId(){
        when(this.repo.existsById(userId)).thenReturn(false);
        service.deleteUser(userId);
        verify(repo, times(1)).existsById(userId);
    }
}

