package com.qa.hwa.dto;

import com.qa.hwa.domain.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class GameSessionDTO {
    private Long sessionId;
    private User user;
    private String gameName;
    private Duration TimePlayed;
    private LocalDateTime timeOfSession;

    public GameSessionDTO(){}

    public GameSessionDTO(User user, String gameName, Duration timePlayed, LocalDateTime timeOfSession) {
        this.user = user;
        this.gameName = gameName;
        TimePlayed = timePlayed;
        this.timeOfSession = timeOfSession;
    }

    public GameSessionDTO(Long sessionId, User user, String gameName, Duration timePlayed, LocalDateTime timeOfSession) {
        this.sessionId = sessionId;
        this.user = user;
        this.gameName = gameName;
        TimePlayed = timePlayed;
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

    public void setUser(User user) {
        this.user = user;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Duration getTimePlayed() {
        return TimePlayed;
    }

    public void setTimePlayed(Duration timePlayed) {
        TimePlayed = timePlayed;
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
        GameSessionDTO that = (GameSessionDTO) o;
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
