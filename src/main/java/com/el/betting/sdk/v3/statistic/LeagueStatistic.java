package com.el.betting.sdk.v3.statistic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents statistics about league.
 * How many time home team Wins, or away team wins or draws.
 */
@Document
@CompoundIndex(name = "leagueStatistics_compound_index", def = "{'category' : 1, 'league' : 1}", unique = true, sparse = true, background = true)
public class LeagueStatistic {
    @Id
    private String id;
    @Indexed
    private final String category;
    @Indexed
    private final String league;

    /** The number of game count for which statistics has been calculated **/
    private int statSize;
    /** The percent of cases where home team wins **/
    private double homeTeamWinPercent;
    private double drawPercent;
    private double awayTeamWinPercent;

    /** The percent of cases where games ends up under 0.5 **/
    private double under05;
    private double under15;
    private double under25;
    private double under35;
    private double under45;
    private double under55;
    private double under65;

    public LeagueStatistic(String category, String league) {
        this.category = category;
        this.league = league;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public String getLeague() {
        return league;
    }

    public int getStatSize() {
        return statSize;
    }

    public void setStatSize(int statSize) {
        this.statSize = statSize;
    }

    public double getHomeTeamWinPercent() {
        return homeTeamWinPercent;
    }

    public void setHomeTeamWinPercent(double homeTeamWinPercent) {
        this.homeTeamWinPercent = homeTeamWinPercent;
    }

    public double getDrawPercent() {
        return drawPercent;
    }

    public void setDrawPercent(double drawPercent) {
        this.drawPercent = drawPercent;
    }

    public double getAwayTeamWinPercent() {
        return awayTeamWinPercent;
    }

    public void setAwayTeamWinPercent(double awayTeamWinPercent) {
        this.awayTeamWinPercent = awayTeamWinPercent;
    }

    public double getUnder05() {
        return under05;
    }

    public void setUnder05(double under05) {
        this.under05 = under05;
    }

    public double getUnder15() {
        return under15;
    }

    public void setUnder15(double under15) {
        this.under15 = under15;
    }

    public double getUnder25() {
        return under25;
    }

    public void setUnder25(double under25) {
        this.under25 = under25;
    }

    public double getUnder35() {
        return under35;
    }

    public void setUnder35(double under35) {
        this.under35 = under35;
    }

    public double getUnder45() {
        return under45;
    }

    public void setUnder45(double under45) {
        this.under45 = under45;
    }

    public double getUnder55() {
        return under55;
    }

    public void setUnder55(double under55) {
        this.under55 = under55;
    }

    public double getUnder65() {
        return under65;
    }

    public void setUnder65(double under65) {
        this.under65 = under65;
    }

    @Override
    public String toString() {
        return "LeagueStatistic{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", league='" + league + '\'' +
                ", statSize=" + statSize +
                ", homeTeamWinPercent=" + homeTeamWinPercent +
                ", drawPercent=" + drawPercent +
                ", awayTeamWinPercent=" + awayTeamWinPercent +
                ", under05=" + under05 +
                ", under15=" + under15 +
                ", under25=" + under25 +
                ", under35=" + under35 +
                ", under45=" + under45 +
                ", under55=" + under55 +
                ", under65=" + under65 +
                '}';
    }
}
