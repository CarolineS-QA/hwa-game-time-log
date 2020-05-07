package com.qa.hwa.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hwa.domain.User;
import com.qa.hwa.dto.UserDTO;
import com.qa.hwa.repo.UsersRepository;
import org.junit.Before;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private UsersRepository repo;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();
    private User testUser;
    private User testUserWithId;
    private Long userId;
    private UserDTO userDTO;
    private Duration time;

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUp(){
        this.repo.deleteAll();
        this.testUser = new User("testUser", time, time, time);
        this.testUserWithId = this.repo.save(testUser);
        this.userId = testUserWithId.getUserId();
        this.userDTO = this.mapToDTO(testUserWithId);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        List<UserDTO> noteDTOList = new ArrayList<>();
        noteDTOList.add(userDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllUsers")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(noteDTOList));
    }

    @Test
    public void createUsersTest() throws Exception {
        String result = this.mock.perform(
                request(HttpMethod.POST, "/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testUser))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(userDTO));
    }
}
