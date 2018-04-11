package com.el.betting.sdk.v3.statistic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent statistic about the minute and the price change for that minute from previous minute.
 * Also contains information the goal number, and weather goal  was scored for that minute.
 */
@Document
public class EventPriceStatistic implements Serializable {
    @Indexed
    private String league;
    @Indexed
    private String homeTeam;
    @Indexed
    private String awayTeam;
    private int homeTeamGoalCount;
    private int awayTeamGoalCount;
    @Indexed
    private LocalDateTime startTime;
    private List<EventMinuteStatistic> eventMinuteStatistics = new ArrayList<>();
    @Indexed
    private String category;

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamGoalCount() {
        return homeTeamGoalCount;
    }

    public void setHomeTeamGoalCount(int homeTeamGoalCount) {
        this.homeTeamGoalCount = homeTeamGoalCount;
    }

    public int getAwayTeamGoalCount() {
        return awayTeamGoalCount;
    }

    public void setAwayTeamGoalCount(int awayTeamGoalCount) {
        this.awayTeamGoalCount = awayTeamGoalCount;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public List<EventMinuteStatistic> getEventMinuteStatistics() {
        return eventMinuteStatistics;
    }

    public void setEventMinuteStatistics(List<EventMinuteStatistic> eventMinuteStatistics) {
        this.eventMinuteStatistics = eventMinuteStatistics;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
