package com.el.betting.sdk.v3.statistic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
//TODO: this is EventSummary rather than Statistic
//TODO: this is team based event summary
public class EventStatistic implements Serializable {
    @Indexed
    private String league;
    @Id
    private String description;
    @Indexed
    private String homeTeam;
    @Indexed
    private String awayTeam;
    private int homeTeamGoalCount;
    private int awayTeamGoalCount;
    @Indexed
    private LocalDateTime startTime;
    private List<GoalStatistic> goalStatistics = new ArrayList<>();
    @Indexed
    private String category;

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public List<GoalStatistic> getGoalStatistics() {
        return goalStatistics;
    }

    public void addGoalStatistic(GoalStatistic goalStatistic) {
        goalStatistics.add(goalStatistic);
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

    public int getGoalCount() {
        return homeTeamGoalCount + awayTeamGoalCount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
