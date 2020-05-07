package com.qa.hwa.service;

import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
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

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceUnitTest {

    @InjectMocks
    private UserService userService;

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
    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp(){
        this.userList = new ArrayList<>();
        time = Duration.ofHours(0);
        this.testUser = new User("testUser", time, time, time);
        this.userList.add(testUser);
        this.testUserWithId = new User(testUser.getUsername(), testUser.getTotalTimePlayed(), testUser.getFreeTime(), testUser.getTimeRemaining());
        this.testUserWithId.setUserId(userId);
        this.userDTO = this.mapToDTO(testUserWithId);
    }

    @Test
    public void readAllUsersTest(){
        when(repo.findAll()).thenReturn(this.userList);
        when(this.mapper.map(testUserWithId, UserDTO.class)).thenReturn(userDTO);
        assertFalse("Service returned no Users", this.userService.readAllUsers().isEmpty());
        verify(repo, times(1)).findAll();
    }
}
