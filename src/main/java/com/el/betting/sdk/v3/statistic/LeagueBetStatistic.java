package com.el.betting.sdk.v3.statistic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Contains statistics about for league, with
 * won bet count and lost bet count done in given laegue.
 */
@Document
public class LeagueBetStatistic {
    @Id
    private String id;
    @Indexed
    private String league;
    private String category;
    private int betCount;
    private int wonCount;
    private int lostCount;
    private double lostPercent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
