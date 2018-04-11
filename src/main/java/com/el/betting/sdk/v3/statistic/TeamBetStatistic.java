package com.el.betting.sdk.v3.statistic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Cotnains bet statistics for the team, with won and lost games count.
 */
@Document
public class TeamBetStatistic {
    private String league;
    private String category;
    @Id
    private String team;
    private int betCount;
    private int wonCount;
    private int lostCount;
    private double lostPercent;

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getBetCount() {
        return betCount;
    }

    public void setBetCount(int betCount) {
        this.betCount = betCount;
    }

    public int getWonCount() {
        return wonCount;
    }

    public void setWonCount(int wonCount) {
        this.wonCount = wonCount;
    }

    public int getLostCount() {
        return lostCount;
    }

    public void setLostCount(int lostCount) {
        this.lostCount = lostCount;
    }

    public double getLostPercent() {
        return lostPercent;
    }

    public void setLostPercent(double lostPercent) {
        this.lostPercent = lostPercent;
    }
}
