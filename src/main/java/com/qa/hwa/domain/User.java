package com.qa.hwa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private Duration totalTimePlayed;
    private Duration freeTime;
    private Duration timeRemaining;

    public User(){}

    public User(String username, Duration totalTimePlayed, Duration freeTime, Duration timeRemaining) {
        this.username = username;
        this.totalTimePlayed = totalTimePlayed;
        this.freeTime = freeTime;
        this.timeRemaining = timeRemaining;
    }

    public User(Long userId, String username, Duration totalTimePlayed, Duration freeTime, Duration timeRemaining) {
        this.userId = userId;
        // @ManytoOne? @OnetoOne (target entity = GameSession.class)
        this.username = username;
        this.totalTimePlayed = totalTimePlayed;
        this.freeTime = freeTime;
        this.timeRemaining = timeRemaining;
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
}