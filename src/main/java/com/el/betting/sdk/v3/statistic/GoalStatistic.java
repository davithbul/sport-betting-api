package com.el.betting.sdk.v3.statistic;

import com.el.betting.sdk.v3.metadata.AccuracyLevel;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GoalStatistic implements Serializable {
    //the goal number of the game
    private int goalNumber;
    //the goal number for the team
    private String teamName;
    private int teamGoalNumber;
    private AccuracyLevel accuracy;
    private String scorer;
    private LocalDateTime goalTime;
    private int goalMinute;

    public AccuracyLevel getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(AccuracyLevel accuracy) {
        this.accuracy = accuracy;
    }

    public int getGoalNumber() {
        return goalNumber;
    }

    public void setGoalNumber(int goalNumber) {
        this.goalNumber = goalNumber;
    }

    public int getTeamGoalNumber() {
        return teamGoalNumber;
    }

    public void setTeamGoalNumber(int teamGoalNumber) {
        this.teamGoalNumber = teamGoalNumber;
    }

    public String getScorer() {
        return scorer;
    }

    public void setScorer(String scorer) {
        this.scorer = scorer;
    }

    public LocalDateTime getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(LocalDateTime goalTime) {
        this.goalTime = goalTime;
    }

    public int getGoalMinute() {
        return goalMinute;
    }

    public void setGoalMinute(int goalMinute) {
        this.goalMinute = goalMinute;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
