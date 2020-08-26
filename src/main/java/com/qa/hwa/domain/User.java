package com.qa.hwa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Document(collection = "gamers")
public class User {

    //MongoDB document '_id' field is handled during document creation
    //@Field("_id")
    //@JsonIgnore
    //@Id
    private Long userId;
    //would like this to be unique
    private String username;
    // recommended time avaliable in a week, resetting on a weekly basis
    private Duration freeTime;
    // based off of game sessions
    private Duration totalTimePlayed;
    // equal to freeTime minus totalTimePlayed
    private Duration timeRemaining;
    // MongoDB has no problems storing nested objects / lists
    // but relationships seem limited to parental unless a separate document stores the appropiate keys
    private List<GameSession> gameSessions = new ArrayList<>();

    public User(){}

    public User(String username, Duration totalTimePlayed, Duration freeTime, Duration timeRemaining, List<GameSession> gameSessions) {
        this.username = username;
        this.totalTimePlayed = totalTimePlayed;
        this.freeTime = freeTime;
        this.timeRemaining = timeRemaining;
        this.gameSessions = gameSessions;
    }

    public User(Long userId, String username, Duration totalTimePlayed, Duration freeTime, Duration timeRemaining, List<GameSession> gameSessions) {
        this.userId = userId;
        this.username = username;
        this.totalTimePlayed = totalTimePlayed;
        this.freeTime = freeTime;
        this.timeRemaining = timeRemaining;
        this.gameSessions = gameSessions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Duration getTotalTimePlayed() {
        return totalTimePlayed;
    }

    public void setTotalTimePlayed(Duration totalTimePlayed) {
        this.totalTimePlayed = totalTimePlayed;
    }

    public Duration getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Duration freeTime) {
        this.freeTime = freeTime;
    }

    public Duration getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(Duration timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public List<GameSession> getGameSessions() {
        return gameSessions;
    }

    public void setGameSessions(List<GameSession> gameSessions) {
        this.gameSessions = gameSessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getTotalTimePlayed(), user.getTotalTimePlayed()) &&
                Objects.equals(getFreeTime(), user.getFreeTime()) &&
                Objects.equals(getTimeRemaining(), user.getTimeRemaining()) &&
                Objects.equals(getGameSessions(), user.getGameSessions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getTotalTimePlayed(), getFreeTime(), getTimeRemaining(), getGameSessions());
    }
}