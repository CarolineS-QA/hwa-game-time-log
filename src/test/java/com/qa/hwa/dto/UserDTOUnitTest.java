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

    @Test
    public void notEqualsWithNull() {
        assertNotEquals(null, userWithId);
    }

    @Test
    public void notEqualsWithDifferentObject() {
        assertNotEquals(userWithId, new Object());
    }

    @Test
    public void checkEquality() {
        assertEquals(userWithId, userWithId);
    }

    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertEquals(userWithId, otherWithId);
    }

    @Test
    public void usernameNullButOtherNameNotNull() {
        userWithId.setUsername(null);
        assertNotEquals(userWithId, otherWithId);
    }

    @Test
    public void usernameNotEqual() {
        otherWithId.setUsername("Not Today");
        assertNotEquals(userWithId, otherWithId);
    }

    @Test
    public void checkEqualityBetweenDifferentObjectsNullUsername() {
        userWithId.setUsername(null);
        otherWithId.setUsername(null);
        assertEquals(userWithId, otherWithId);
    }

    @Test
    public void nullId() {
        userWithId.setUserId(null);
        assertNotEquals(userWithId, otherWithId);
    }

    @Test
    public void nullIdOnBoth() {
        userWithId.setUserId(null);
        otherWithId.setUserId(null);
        assertEquals(userWithId, otherWithId);
    }

    @Test
    public void otherIdDifferent() {
        otherWithId.setUserId(2L);
        assertNotEquals(userWithId, otherWithId);
    }

    @Test
    public void nullTotalTimePlayed() {
        userWithId.setTotalTimePlayed(null);
        assertNotEquals(userWithId, otherWithId);
    }

    @Test
    public void nullTotalTimePlayedOnBoth() {
        userWithId.setTotalTimePlayed(null);
        otherWithId.setTotalTimePlayed(null);
        assertEquals(userWithId, otherWithId);
    }

    @Test
    public void otherTotalTimePlayedDifferent() {
        otherWithId.setTotalTimePlayed(Duration.ofHours(2));
        assertNotEquals(userWithId, otherWithId);
    }

    @Test
    public void nullFreeTime() {
        userWithId.setFreeTime(null);
        assertNotEquals(userWithId, otherWithId);
    }

    @Test
    public void nullFreeTimeOnBoth() {
        userWithId.setFreeTime(null);
        otherWithId.setFreeTime(null);
        assertEquals(userWithId, otherWithId);
    }

    @Test
    public void otherFreeTimeDifferent() {
        otherWithId.setFreeTime(Duration.ofHours(2));
        assertNotEquals(userWithId, otherWithId);
    }

    @Test
    public void nullTimeRemaining() {
        userWithId.setTimeRemaining(null);
        assertNotEquals(userWithId, otherWithId);
    }

    @Test
    public void nullTimeRemainingOnBoth() {
        userWithId.setTimeRemaining(null);
        otherWithId.setTimeRemaining(null);
        assertEquals(userWithId, otherWithId);
    }

    @Test
    public void otherTimeRemainingDifferent() {
        otherWithId.setTimeRemaining(Duration.ofHours(2));
        assertNotEquals(userWithId, otherWithId);
    }


    @Test
    public void hashCodeTest() {
        assertEquals(userWithId.hashCode(), otherWithId.hashCode());
    }

    @Test
    public void hashCodeTestWithNull() {
        User user = new User(null, null, null, null);
        User other = new User(null, null, null, null);
        assertEquals(user.hashCode(), other.hashCode());
    }
}
