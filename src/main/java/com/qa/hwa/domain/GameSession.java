package com.qa.hwa.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "sessions")
public class GameSession {

    @Id
    private Long sessionId;
    // Relationships in MongoDB are harder to define than mySQL
    // might not need to be of type User
    private User user;
    private String gameName;
    private Duration timePlayed;
    //timestamp for tracking purposes
    private LocalDateTime timeOfSession;

    public GameSession(){}

    public GameSession(User user, String gameName, Duration timePlayed, LocalDateTime timeOfSession) {
        this.user = user;
        this.gameName = gameName;
        this.timePlayed = timePlayed;
        this.timeOfSession = timeOfSession;
    }

    public GameSession(Long sessionId, User user, String gameName, Duration timePlayed, LocalDateTime timeOfSession) {
        this.sessionId = sessionId;
        this.user = user;
        this.gameName = gameName;
        this.timePlayed = timePlayed;
        this.timeOfSession = timeOfSession;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User username) {
        this.user = username;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Duration getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(Duration timePlayed) {
        this.timePlayed = timePlayed;
    }

    public LocalDateTime getTimeOfSession() {
        return timeOfSession;
    }

    public void setTimeOfSession(LocalDateTime timeOfSession) {
        this.timeOfSession = timeOfSession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSession that = (GameSession) o;
        return Objects.equals(getSessionId(), that.getSessionId()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getGameName(), that.getGameName()) &&
                Objects.equals(getTimePlayed(), that.getTimePlayed()) &&
                Objects.equals(getTimeOfSession(), that.getTimeOfSession());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionId(), getUser(), getGameName(), getTimePlayed(), getTimeOfSession());
    }
}
