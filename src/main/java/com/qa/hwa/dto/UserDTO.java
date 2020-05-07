package com.qa.hwa.dto;

import java.time.Duration;
import java.util.Objects;

public class UserDTO {
    private Long userId;
    private String username;
    private Duration totalTimePlayed;
    private Duration freeTime;
    private Duration timeRemaining;

    public UserDTO(){}

    public UserDTO(String username, Duration totalTimePlayed, Duration freeTime, Duration timeRemaining) {
        this.username = username;
        this.totalTimePlayed = totalTimePlayed;
        this.freeTime = freeTime;
        this.timeRemaining = timeRemaining;
    }

    public UserDTO(Long userId, String username, Duration totalTimePlayed, Duration freeTime, Duration timeRemaining) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getUserId(), userDTO.getUserId()) &&
                Objects.equals(getUsername(), userDTO.getUsername()) &&
                Objects.equals(getTotalTimePlayed(), userDTO.getTotalTimePlayed()) &&
                Objects.equals(getFreeTime(), userDTO.getFreeTime()) &&
                Objects.equals(getTimeRemaining(), userDTO.getTimeRemaining());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getTotalTimePlayed(), getFreeTime(), getTimeRemaining());
    }
}
