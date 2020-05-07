package com.qa.hwa.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;

public class UserUnitTest {

    private User userWithId;
    private User userWithoutId;
    private User emptyUser;
    private User otherWithId;
    private Duration zeroDuration;

    @Before
    public void SetUp()
    {
        zeroDuration = Duration.ofDays(0);
        userWithId = new User(1L, "testUser", zeroDuration, zeroDuration, zeroDuration);
        otherWithId = new User(1L, "testUser", zeroDuration, zeroDuration, zeroDuration);
        userWithoutId = new User("testUser", zeroDuration, zeroDuration, zeroDuration);
        emptyUser = new User();
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
