package com.qa.hwa.domain;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;
    @ManyToOne (targetEntity = User.class, fetch = FetchType.LAZY)
    private User username;
    private String gameName;
    private Duration timePlayed;
    private LocalDateTime timeOfSession;

    public GameSession(){}

    public GameSession(User username, String gameName, Duration timePlayed, LocalDateTime timeOfSession) {
        this.username = username;
        this.gameName = gameName;
        this.timePlayed = timePlayed;
        this.timeOfSession = timeOfSession;
    }

    public GameSession(Long sessionId, User username, String gameName, Duration timePlayed, LocalDateTime timeOfSession) {
        this.sessionId = sessionId;
        this.username = username;
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

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
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
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getGameName(), that.getGameName()) &&
                Objects.equals(getTimePlayed(), that.getTimePlayed()) &&
                Objects.equals(getTimeOfSession(), that.getTimeOfSession());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionId(), getUsername(), getGameName(), getTimePlayed(), getTimeOfSession());
    }
}
