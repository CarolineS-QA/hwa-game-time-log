package com.qa.hwa.domain;

import org.junit.Before;

import java.time.Duration;
import java.time.LocalDateTime;

public class GameSessionUnitTest {

    private GameSession sessionWithId;
    private GameSession sessionWithoutId;
    private GameSession emptySession;
    private GameSession otherWithId;
    private Duration zeroTime;
    private LocalDateTime date;
    private User player1;

    @Before
    public void SetUp(){
        zeroTime = Duration.ofHours(0);
        date = LocalDateTime.MIN;
        player1 = new User(1L, "testUser", zeroTime, zeroTime, zeroTime, null);
        sessionWithId = new GameSession(1L, player1, "hello world", zeroTime, date);
        sessionWithoutId = new GameSession(player1, "hello world", zeroTime, date);
        emptySession = new GameSession();
        otherWithId = new GameSession(1L, player1, "hello world", zeroTime, date);
    }
}
