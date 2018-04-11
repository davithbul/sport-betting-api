package com.el.betting.sdk.v3.statistic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents team descriptive statistics, for analyzing team stability
 * over the time and over the teams.
 */
@Document
@CompoundIndex(name = "teamDescriptiveStats_compound_index", def = "{'category' : 1, 'league' : 1, 'team' : 1}", unique = true, sparse = true, background = true)
public class TeamDescriptiveStats {
    @Id
    private String id;

    @Indexed
    private final String team;
    @Indexed
    private final String league;
    @Indexed
    private final String category;
    private GoalsDescriptiveStats homeScoredStats;
    private GoalsDescriptiveStats homeConcededStats;
    private GoalsDescriptiveStats awayScoredStats;
    private GoalsDescriptiveStats awayConcededStats;


    public TeamDescriptiveStats(String team, String league, String category) {
        this.team = team;
        this.league = league;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public String getLeague() {
        return league;
    }

    public String getCategory() {
        return category;
    }

    public GoalsDescriptiveStats getHomeScoredStats() {
        return homeScoredStats;
    }

    public void setHomeScoredStats(GoalsDescriptiveStats homeScoredStats) {
        this.homeScoredStats = homeScoredStats;
    }

    public GoalsDescriptiveStats getHomeConcededStats() {
        return homeConcededStats;
    }

    public void setHomeConcededStats(GoalsDescriptiveStats homeConcededStats) {
        this.homeConcededStats = homeConcededStats;
    }

    public GoalsDescriptiveStats getAwayScoredStats() {
        return awayScoredStats;
    }

    public void setAwayScoredStats(GoalsDescriptiveStats awayScoredStats) {
        this.awayScoredStats = awayScoredStats;
    }

    public GoalsDescriptiveStats getAwayConcededStats() {
        return awayConcededStats;
    }

    public void setAwayConcededStats(GoalsDescriptiveStats awayConcededStats) {
        this.awayConcededStats = awayConcededStats;
    }
}
