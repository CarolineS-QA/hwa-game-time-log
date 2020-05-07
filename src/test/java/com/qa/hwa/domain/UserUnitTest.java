package com.qa.hwa.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;

public class UserUnitTest {

    private User user;
    private User other;
    private Duration zeroDuration;

    @Before
    public void SetUp()
    {
        zeroDuration = Duration.ofDays(0);
        user = new User(1L, "testUser", zeroDuration, zeroDuration, zeroDuration);
        other = new User(1L, "testUser", zeroDuration, zeroDuration, zeroDuration);
    }

    @Test
    public void gettersAndSettersTest() {
        assertNotNull(user.getUserId());
        assertNotNull(user.getUsername());
        assertNotNull(user.getTotalTimePlayed());
        assertNotNull(user.getFreeTime());
        assertNotNull(user.getTimeRemaining());

        user.setUserId(null);
        assertNull(user.getUserId());
        user.setUsername(null);
        assertNull(user.getUsername());
        user.setTotalTimePlayed(null);
        assertNull(user.getTotalTimePlayed());
        user.setFreeTime(null);
        assertNull(user.getFreeTime());
        user.setTimeRemaining(null);
        assertNull(user.getTimeRemaining());
    }

    @Test
    public void constructorUserWithId() {
        assertEquals(1L, user.getUserId(), 0);
        assertEquals("testUser", user.getUsername());
        assertEquals(Duration.ofDays(0), user.getTotalTimePlayed());
        assertEquals(Duration.ofDays(0), user.getFreeTime());
        assertEquals(Duration.ofDays(0), user.getTimeRemaining());
    }

    @Test
    public void constructorWithoutId() {
        User user = new User("testUser", zeroDuration, zeroDuration, zeroDuration);
        assertNull(user.getUserId());
        assertNotNull(user.getUsername());
        assertNotNull(user.getTotalTimePlayed());
        assertNotNull(user.getFreeTime());
        assertNotNull(user.getTimeRemaining());
    }

}
