package com.qa.hwa.dto;

import com.qa.hwa.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class UserDTOUnitTest {
    private UserDTO userWithId;
    private UserDTO userWithoutId;
    private UserDTO emptyUser;
    private UserDTO otherWithId;
    private Duration zeroDuration;

    @Before
    public void SetUp()
    {
        zeroDuration = Duration.ofDays(0);
        userWithId = new UserDTO(1L, "testUser", zeroDuration, zeroDuration, zeroDuration);
        otherWithId = new UserDTO(1L, "testUser", zeroDuration, zeroDuration, zeroDuration);
        userWithoutId = new UserDTO("testUser", zeroDuration, zeroDuration, zeroDuration);
        emptyUser = new UserDTO();
    }

    @Test
    public void gettersAndSettersTest() {
        assertNotNull(userWithId.getUserId());
        assertNotNull(userWithId.getUsername());
        assertNotNull(userWithId.getTotalTimePlayed());
        assertNotNull(userWithId.getFreeTime());
        assertNotNull(userWithId.getTimeRemaining());

        userWithId.setUserId(null);
        assertNull(userWithId.getUserId());
        userWithId.setUsername(null);
        assertNull(userWithId.getUsername());
        userWithId.setTotalTimePlayed(null);
        assertNull(userWithId.getTotalTimePlayed());
        userWithId.setFreeTime(null);
        assertNull(userWithId.getFreeTime());
        userWithId.setTimeRemaining(null);
        assertNull(userWithId.getTimeRemaining());
    }

    @Test
    public void emptyConstructor(){
        assertNotNull(emptyUser);
        assertNull(emptyUser.getUserId());
        assertNull(emptyUser.getUsername());
        assertNull(emptyUser.getTotalTimePlayed());
        assertNull(emptyUser.getFreeTime());
        assertNull(emptyUser.getTimeRemaining());
    }
    @Test
    public void constructorUserWithId() {
        assertEquals(1L, userWithId.getUserId(), 0);
        assertEquals("testUser", userWithId.getUsername());
        assertEquals(Duration.ofDays(0), userWithId.getTotalTimePlayed());
        assertEquals(Duration.ofDays(0), userWithId.getFreeTime());
        assertEquals(Duration.ofDays(0), userWithId.getTimeRemaining());
    }

    @Test
    public void constructorWithoutId() {
        assertNull(userWithoutId.getUserId());
        assertNotNull(userWithoutId.getUsername());
        assertNotNull(userWithoutId.getTotalTimePlayed());
        assertNotNull(userWithoutId.getFreeTime());
        assertNotNull(userWithoutId.getTimeRemaining());
    }
}
