package com.qa.hwa.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GameSessionUnitTest {

    private GameSession sessionWithId;
    private GameSession sessionWithoutId;
    private GameSession emptySession;
    private GameSession otherWithId;
    private Duration zeroTime;
    private LocalDateTime date;
    private User player1;
    private User player2;

    @Before
    public void SetUp(){
        zeroTime = Duration.ofHours(0);
        date = LocalDateTime.MIN;
        player1 = new User(1L, "testUser", zeroTime, zeroTime, zeroTime, null);
        player2 = new User(1L, "aloha", zeroTime, zeroTime, zeroTime, null);
        sessionWithId = new GameSession(1L, player1, "hello world", zeroTime, date);
        sessionWithoutId = new GameSession(player1, "hello world", zeroTime, date);
        emptySession = new GameSession();
        otherWithId = new GameSession(1L, player1, "hello world", zeroTime, date);
    }

    @Test
    public void gettersAndSettersTest() {
        assertNotNull(sessionWithId.getSessionId());
        assertNotNull(sessionWithId.getUser());
        assertNotNull(sessionWithId.getGameName());
        assertNotNull(sessionWithId.getTimePlayed());
        assertNotNull(sessionWithId.getTimeOfSession());

        sessionWithId.setSessionId(null);
        assertNull(sessionWithId.getSessionId());
        sessionWithId.setUser(null);
        assertNull(sessionWithId.getUser());
        sessionWithId.setGameName(null);
        assertNull(sessionWithId.getGameName());
        sessionWithId.setTimePlayed(null);
        assertNull(sessionWithId.getTimePlayed());
        sessionWithId.setTimeOfSession(null);
        assertNull(sessionWithId.getTimeOfSession());
    }

    @Test
    public void emptyConstructor(){
        assertNotNull(emptySession);
        assertNull(emptySession.getSessionId());
        assertNull(emptySession.getUser());
        assertNull(emptySession.getGameName());
        assertNull(emptySession.getTimePlayed());
        assertNull(emptySession.getTimeOfSession());
    }
    @Test
    public void constructorUserWithId() {
        assertEquals(1L, sessionWithId.getSessionId(), 0);
        assertEquals(player1, sessionWithId.getUser());
        assertEquals("hello world", sessionWithId.getGameName());
        assertEquals(zeroTime, sessionWithId.getTimePlayed());
        assertEquals(date, sessionWithId.getTimeOfSession());
    }

    @Test
    public void constructorWithoutId() {
        assertNull(sessionWithoutId.getSessionId());
        assertNotNull(sessionWithoutId.getUser());
        assertNotNull(sessionWithoutId.getGameName());
        assertNotNull(sessionWithoutId.getTimePlayed());
        assertNotNull(sessionWithoutId.getTimeOfSession());
    }

    @Test
    public void notEqualsWithNull() {
        assertNotEquals(null, sessionWithId);
    }

    @Test
    public void notEqualsWithDifferentObject() {
        assertNotEquals(sessionWithId, new Object());
    }

    @Test
    public void checkEquality() {
        assertEquals(sessionWithId, sessionWithId);
    }

    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertEquals(sessionWithId, otherWithId);
    }

    @Test
    public void usernameNullButOtherNameNotNull() {
        sessionWithId.setUser(null);
        assertNotEquals(sessionWithId, otherWithId);
    }

    @Test
    public void usernameNotEqual() {
        otherWithId.setUser(player2);
        assertNotEquals(sessionWithId, otherWithId);
    }

    @Test
    public void checkEqualityBetweenDifferentObjectsNullUsername() {
        sessionWithId.setUser(null);
        otherWithId.setUser(null);
        assertEquals(sessionWithId, otherWithId);
    }

    @Test
    public void nullId() {
        sessionWithId.setSessionId(null);
        assertNotEquals(sessionWithId, otherWithId);
    }

    @Test
    public void nullIdOnBoth() {
        sessionWithId.setSessionId(null);
        otherWithId.setSessionId(null);
        assertEquals(sessionWithId, otherWithId);
    }

    @Test
    public void otherIdDifferent() {
        otherWithId.setSessionId(2L);
        assertNotEquals(sessionWithId, otherWithId);
    }

    @Test
    public void nullGameName() {
        sessionWithId.setGameName(null);
        assertNotEquals(sessionWithId, otherWithId);
    }

    @Test
    public void nullGameNameOnBoth() {
        sessionWithId.setGameName(null);
        otherWithId.setGameName(null);
        assertEquals(sessionWithId, otherWithId);
    }

    @Test
    public void otherGameNameDifferent() {
        otherWithId.setGameName("bang bang");
        assertNotEquals(sessionWithId, otherWithId);
    }

    @Test
    public void nullTimePlayed() {
        sessionWithId.setTimePlayed(null);
        assertNotEquals(sessionWithId, otherWithId);
    }

    @Test
    public void nullTimePlayedOnBoth() {
        sessionWithId.setTimePlayed(null);
        otherWithId.setTimePlayed(null);
        assertEquals(sessionWithId, otherWithId);
    }

    @Test
    public void otherTimePlayedDifferent() {
        otherWithId.setTimePlayed(Duration.ofHours(2));
        assertNotEquals(sessionWithId, otherWithId);
    }

    @Test
    public void nullTimeOfSession() {
        sessionWithId.setTimeOfSession(null);
        assertNotEquals(sessionWithId, otherWithId);
    }

    @Test
    public void nullTimeOfSessionBoth() {
        sessionWithId.setTimeOfSession(null);
        otherWithId.setTimeOfSession(null);
        assertEquals(sessionWithId, otherWithId);
    }

    @Test
    public void otherTimeOfSessionDifferent() {
        otherWithId.setTimeOfSession(LocalDateTime.MAX);
        assertNotEquals(sessionWithId, otherWithId);
    }


    @Test
    public void hashCodeTest() {
        assertEquals(sessionWithId.hashCode(), otherWithId.hashCode());
    }

    @Test
    public void hashCodeTestWithNull() {
        GameSession session = new GameSession( null, null, null, null);
        GameSession other = new GameSession(null, null, null, null);
        assertEquals(session.hashCode(), other.hashCode());
    }
}
