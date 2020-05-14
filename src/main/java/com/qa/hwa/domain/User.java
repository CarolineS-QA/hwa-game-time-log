package com.qa.hwa.domain;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;
import java.util.Objects;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column (unique = true)
    private String username;
    private Duration totalTimePlayed;
    private Duration freeTime;
    private Duration timeRemaining;
    @OneToMany(mappedBy = "user")
    private List<GameSession> gameSessions;

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