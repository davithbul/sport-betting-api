package com.el.betting.sdk.v3.statistic;

import com.el.betting.sdk.v2.TimeRange;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Defines team's strategy and mindset. Are they offencive, defencive,
 * or very stable after scoring goals.
 * How the team behaves when concedes goals, does it go all ahead and scores or become more
 * unorganized and concedes more goals.
 * Does it keep it's minimal advantage or tries to increase it.
 * What is strategy against better teams, how they play against offencive or defencive teams.
 * <p>
 * Unlike TeamDescriptive stats, Team strategy mostly represents team behaviour in a certain
 * situations.
 */
@Document
@CompoundIndex(name = "teamStrategy_compound_index", def = "{'category' : 1, 'league' : 1, 'team' : 1}", unique = true, sparse = true, background = true)
public class TeamStrategy {
    @Id
    private String id;

    @Indexed
    private final String team;
    @Indexed
    private final String league;
    @Indexed
    private final String category;

    private int homeStatsSize;
    private int awayStatsSize;

    private double winPercent;
    private double drawPercent;
    private double losePercent;

    private double winPercentAtHome;
    private double drawPercentAtHome;
    private double losePercentAtHome;

    private double winPercentAway;
    private double drawPercentAway;
    private double losePercentAway;

    /**
     * Indicates how much in home games' score and conceded goals count
     * are dependent on each other. If =1 it means there is perfect correlation
     * if it's -1 it means it has absolute negative (opposite) correlation.
     * Any other value between [-1; 1] shows strongest of correlation.
     */
    private double homeGamesCorrelation;
    private double awayGamesCorrelation;

    /**
     * The average of scored minus conceded goal cout at home.
     */
    private double avgMarginAtHome;
    private int modeMarginAtHome;
    private double avgMarginAway;
    private int modeMarginAway;

    /**
     * Shows average score difference after scoring first
     */
    private Double avgMarginAfterScoringFirstAtHome;
    private Double avgMarginAfterScoringFirstAway;

    /**
     * Shows most frequent score difference after scoring first
     */
    private Integer modeMarginAfterScoringFirstAtHome;
    private Integer modeMarginAfterScoringFirstAway;

    private Double avgMarginAfterConcedingFirstAtHome;
    private Double avgMarginAfterConcedingFirstAway;
    private Integer modeMarginAfterConcedingFirstAtHome;
    private Integer modeMarginAfterConcedingFirstAway;

    /**
     * Time range which represents first goal score minute.
     * The first number indicates the earliest minute when team
     * scores goal, The second number indicates the most frequent
     * first goal scoring minute.
     */
    private TimeRange<Integer> firstGoalScoreMinuteAtHome;
    private TimeRange<Integer> firstGoalScoreMinuteAway;
    private TimeRange<Integer> firstGoalConcedeMinuteAtHome;
    private TimeRange<Integer> firstGoalConcedeMinuteAway;

    private TimeRange<Integer> lastGoalScoreMinuteAtHome;
    private TimeRange<Integer> lastGoalScoreMinuteAway;
    private TimeRange<Integer> lastGoalConcedeMinuteAtHome;
    private TimeRange<Integer> lastGoalConcedeMinuteAway;

    /**
     * Percent of cases where team scores first goal, between 0-1
     */
    private double scoresFirstPercentAtHome;
    private double scoresFirstPercentAway;
    private double concedeFirstPercentAtHome;
    private double concedeFirstPercentAway;

    private double winsAfterScoringFirstAtHome;
    private double winsAfterScoringFirstAway;
    private double losesAfterScoringFirstAtHome;
    private double losesAfterScoringFirstAway;
    private double drawsAfterScoringFirstAtHome;
    private double drawsAfterScoringFirstAway;

    private double winsAfterConcedingFirstAtHome;
    private double winsAfterConcedingFirstAway;
    private double losesAfterConcedingFirstAtHome;
    private double losesAfterConcedingFirstAway;
    private double drawsAfterConcedingFirstAtHome;
    private double drawsAfterConcedingFirstAway;

    /**
     * represents range of minutes, first one is the earliest when team
     * recovers after conceding goal first, the second is the most frequent.
     */
    private TimeRange<Integer> avgTimeToScoreAfterConcedingAtHome;
    private TimeRange<Integer> avgTimeToScoreAfterConcedingAway;

    /**
     * Percent of cases when team concedes after scoring.
     */
    private double concedeAfterScoringPercentAtHome;
    private double concedeAfterScoringPercentAway;

    /**
     * Percent of cases when team scores after conceding
     */
    private double scoresAfterConcedingPercentAtHome;
    private double scoresAfterConcedingPercentAway;

    /**
     * Percent of cases when team doubles it's advantage after scoring first
     */
    private double doublesAfterScoringPercentAtHome;
    private double doublesAfterScoringPercentAway;

    /**
     * Percent of cases when team concedes second one after conceding first
     */
    private double concedesAfterConcedingPercentAtHome;
    private double concedesAfterConcedingPercentAway;

    /**
     * Percent of cases when team keeps the result after scoring first
     */
    private double defendsAfterScoringFirstPercentAtHome;
    private double defendsAfterScoringFirstPercentAway;

    /**
     * Percent of cases when team keeps the result after conceding first
     */
    private double defendsAfterConcedingFirstPercentAtHome;
    private double defendsAfterConcedingFirstPercentAway;

    /**
     * Percent of cases when team wins after having any score advantage during game.
     */
    private double defendsWinningScorePercentAtHome;
    private double defendsWinningScorePercentAway;

    /**
     * represents range of minutes, first one is the earliest when team
     * recovers after conceding goal first, the second is the most frequent.
     */
    private TimeRange<Integer> avgTimeToConcedeAfterScoringAtHome;
    private TimeRange<Integer> avgTimeToConcedeAfterScoringAway;

    public TeamStrategy(String team, String league, String category) {
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

    public double getHomeGamesCorrelation() {
        return homeGamesCorrelation;
    }

    public void setHomeGamesCorrelation(double homeGamesCorrelation) {
        this.homeGamesCorrelation = homeGamesCorrelation;
    }

    public double getWinPercent() {
        return winPercent;
    }

    public void setWinPercent(double winPercent) {
        this.winPercent = winPercent;
    }

    public double getDrawPercent() {
        return drawPercent;
    }

    public void setDrawPercent(double drawPercent) {
        this.drawPercent = drawPercent;
    }

    public double getLosePercent() {
        return losePercent;
    }

    public void setLosePercent(double losePercent) {
        this.losePercent = losePercent;
    }

    public double getWinPercentAtHome() {
        return winPercentAtHome;
    }

    public void setWinPercentAtHome(double winPercentAtHome) {
        this.winPercentAtHome = winPercentAtHome;
    }

    public double getDrawPercentAtHome() {
        return drawPercentAtHome;
    }

    public void setDrawPercentAtHome(double drawPercentAtHome) {
        this.drawPercentAtHome = drawPercentAtHome;
    }

    public double getLosePercentAtHome() {
        return losePercentAtHome;
    }

    public void setLosePercentAtHome(double losePercentAtHome) {
        this.losePercentAtHome = losePercentAtHome;
    }

    public double getWinPercentAway() {
        return winPercentAway;
    }

    public void setWinPercentAway(double winPercentAway) {
        this.winPercentAway = winPercentAway;
    }

    public double getDrawPercentAway() {
        return drawPercentAway;
    }

    public void setDrawPercentAway(double drawPercentAway) {
        this.drawPercentAway = drawPercentAway;
    }

    public double getLosePercentAway() {
        return losePercentAway;
    }

    public void setLosePercentAway(double losePercentAway) {
        this.losePercentAway = losePercentAway;
    }

    public double getAwayGamesCorrelation() {
        return awayGamesCorrelation;
    }

    public void setAwayGamesCorrelation(double awayGamesCorrelation) {
        this.awayGamesCorrelation = awayGamesCorrelation;
    }

    public double getAvgMarginAtHome() {
        return avgMarginAtHome;
    }

    public void setAvgMarginAtHome(double avgMarginAtHome) {
        this.avgMarginAtHome = avgMarginAtHome;
    }

    public int getModeMarginAtHome() {
        return modeMarginAtHome;
    }

    public void setModeMarginAtHome(int modeMarginAtHome) {
        this.modeMarginAtHome = modeMarginAtHome;
    }

    public double getAvgMarginAway() {
        return avgMarginAway;
    }

    public void setAvgMarginAway(double avgMarginAway) {
        this.avgMarginAway = avgMarginAway;
    }

    public int getModeMarginAway() {
        return modeMarginAway;
    }

    public void setModeMarginAway(int modeMarginAway) {
        this.modeMarginAway = modeMarginAway;
    }

    public Double getAvgMarginAfterScoringFirstAtHome() {
        return avgMarginAfterScoringFirstAtHome;
    }

    public void setAvgMarginAfterScoringFirstAtHome(Double avgMarginAfterScoringFirstAtHome) {
        this.avgMarginAfterScoringFirstAtHome = avgMarginAfterScoringFirstAtHome;
    }

    public Double getAvgMarginAfterScoringFirstAway() {
        return avgMarginAfterScoringFirstAway;
    }

    public void setAvgMarginAfterScoringFirstAway(Double avgMarginAfterScoringFirstAway) {
        this.avgMarginAfterScoringFirstAway = avgMarginAfterScoringFirstAway;
    }

    public Integer getModeMarginAfterScoringFirstAtHome() {
        return modeMarginAfterScoringFirstAtHome;
    }

    public void setModeMarginAfterScoringFirstAtHome(Integer modeMarginAfterScoringFirstAtHome) {
        this.modeMarginAfterScoringFirstAtHome = modeMarginAfterScoringFirstAtHome;
    }

    public Integer getModeMarginAfterScoringFirstAway() {
        return modeMarginAfterScoringFirstAway;
    }

    public void setModeMarginAfterScoringFirstAway(Integer modeMarginAfterScoringFirstAway) {
        this.modeMarginAfterScoringFirstAway = modeMarginAfterScoringFirstAway;
    }

    public Double getAvgMarginAfterConcedingFirstAtHome() {
        return avgMarginAfterConcedingFirstAtHome;
    }

    public void setAvgMarginAfterConcedingFirstAtHome(Double avgMarginAfterConcedingFirstAtHome) {
        this.avgMarginAfterConcedingFirstAtHome = avgMarginAfterConcedingFirstAtHome;
    }

    public Double getAvgMarginAfterConcedingFirstAway() {
        return avgMarginAfterConcedingFirstAway;
    }

    public void setAvgMarginAfterConcedingFirstAway(Double avgMarginAfterConcedingFirstAway) {
        this.avgMarginAfterConcedingFirstAway = avgMarginAfterConcedingFirstAway;
    }

    public Integer getModeMarginAfterConcedingFirstAtHome() {
        return modeMarginAfterConcedingFirstAtHome;
    }

    public void setModeMarginAfterConcedingFirstAtHome(Integer modeMarginAfterConcedingFirstAtHome) {
        this.modeMarginAfterConcedingFirstAtHome = modeMarginAfterConcedingFirstAtHome;
    }

    public Integer getModeMarginAfterConcedingFirstAway() {
        return modeMarginAfterConcedingFirstAway;
    }

    public void setModeMarginAfterConcedingFirstAway(Integer modeMarginAfterConcedingFirstAway) {
        this.modeMarginAfterConcedingFirstAway = modeMarginAfterConcedingFirstAway;
    }

    public TimeRange<Integer> getFirstGoalScoreMinuteAtHome() {
        return firstGoalScoreMinuteAtHome;
    }

    public void setFirstGoalScoreMinuteAtHome(TimeRange<Integer> firstGoalScoreMinuteAtHome) {
        this.firstGoalScoreMinuteAtHome = firstGoalScoreMinuteAtHome;
    }

    public TimeRange<Integer> getFirstGoalScoreMinuteAway() {
        return firstGoalScoreMinuteAway;
    }

    public void setFirstGoalScoreMinuteAway(TimeRange<Integer> firstGoalScoreMinuteAway) {
        this.firstGoalScoreMinuteAway = firstGoalScoreMinuteAway;
    }

    public TimeRange<Integer> getFirstGoalConcedeMinuteAtHome() {
        return firstGoalConcedeMinuteAtHome;
    }

    public void setFirstGoalConcedeMinuteAtHome(TimeRange<Integer> firstGoalConcedeMinuteAtHome) {
        this.firstGoalConcedeMinuteAtHome = firstGoalConcedeMinuteAtHome;
    }

    public TimeRange<Integer> getFirstGoalConcedeMinuteAway() {
        return firstGoalConcedeMinuteAway;
    }

    public void setFirstGoalConcedeMinuteAway(TimeRange<Integer> firstGoalConcedeMinuteAway) {
        this.firstGoalConcedeMinuteAway = firstGoalConcedeMinuteAway;
    }

    public TimeRange<Integer> getLastGoalScoreMinuteAtHome() {
        return lastGoalScoreMinuteAtHome;
    }

    public void setLastGoalScoreMinuteAtHome(TimeRange<Integer> lastGoalScoreMinuteAtHome) {
        this.lastGoalScoreMinuteAtHome = lastGoalScoreMinuteAtHome;
    }

    public TimeRange<Integer> getLastGoalScoreMinuteAway() {
        return lastGoalScoreMinuteAway;
    }

    public void setLastGoalScoreMinuteAway(TimeRange<Integer> lastGoalScoreMinuteAway) {
        this.lastGoalScoreMinuteAway = lastGoalScoreMinuteAway;
    }

    public TimeRange<Integer> getLastGoalConcedeMinuteAtHome() {
        return lastGoalConcedeMinuteAtHome;
    }

    public void setLastGoalConcedeMinuteAtHome(TimeRange<Integer> lastGoalConcedeMinuteAtHome) {
        this.lastGoalConcedeMinuteAtHome = lastGoalConcedeMinuteAtHome;
    }

    public TimeRange<Integer> getLastGoalConcedeMinuteAway() {
        return lastGoalConcedeMinuteAway;
    }

    public void setLastGoalConcedeMinuteAway(TimeRange<Integer> lastGoalConcedeMinuteAway) {
        this.lastGoalConcedeMinuteAway = lastGoalConcedeMinuteAway;
    }

    public double getScoresFirstPercentAtHome() {
        return scoresFirstPercentAtHome;
    }

    public void setScoresFirstPercentAtHome(double scoresFirstPercentAtHome) {
        this.scoresFirstPercentAtHome = scoresFirstPercentAtHome;
    }

    public double getScoresFirstPercentAway() {
        return scoresFirstPercentAway;
    }

    public void setScoresFirstPercentAway(double scoresFirstPercentAway) {
        this.scoresFirstPercentAway = scoresFirstPercentAway;
    }

    public double getConcedeFirstPercentAtHome() {
        return concedeFirstPercentAtHome;
    }

    public void setConcedeFirstPercentAtHome(double concedeFirstPercentAtHome) {
        this.concedeFirstPercentAtHome = concedeFirstPercentAtHome;
    }

    public double getConcedeFirstPercentAway() {
        return concedeFirstPercentAway;
    }

    public void setConcedeFirstPercentAway(double concedeFirstPercentAway) {
        this.concedeFirstPercentAway = concedeFirstPercentAway;
    }

    public double getWinsAfterScoringFirstAtHome() {
        return winsAfterScoringFirstAtHome;
    }

    public void setWinsAfterScoringFirstAtHome(double winsAfterScoringFirstAtHome) {
        this.winsAfterScoringFirstAtHome = winsAfterScoringFirstAtHome;
    }

    public double getWinsAfterScoringFirstAway() {
        return winsAfterScoringFirstAway;
    }

    public void setWinsAfterScoringFirstAway(double winsAfterScoringFirstAway) {
        this.winsAfterScoringFirstAway = winsAfterScoringFirstAway;
    }

    public double getLosesAfterScoringFirstAtHome() {
        return losesAfterScoringFirstAtHome;
    }

    public void setLosesAfterScoringFirstAtHome(double losesAfterScoringFirstAtHome) {
        this.losesAfterScoringFirstAtHome = losesAfterScoringFirstAtHome;
    }

    public double getLosesAfterScoringFirstAway() {
        return losesAfterScoringFirstAway;
    }

    public void setLosesAfterScoringFirstAway(double losesAfterScoringFirstAway) {
        this.losesAfterScoringFirstAway = losesAfterScoringFirstAway;
    }

    public double getDrawsAfterScoringFirstAtHome() {
        return drawsAfterScoringFirstAtHome;
    }

    public void setDrawsAfterScoringFirstAtHome(double drawsAfterScoringFirstAtHome) {
        this.drawsAfterScoringFirstAtHome = drawsAfterScoringFirstAtHome;
    }

    public double getDrawsAfterScoringFirstAway() {
        return drawsAfterScoringFirstAway;
    }

    public void setDrawsAfterScoringFirstAway(double drawsAfterScoringFirstAway) {
        this.drawsAfterScoringFirstAway = drawsAfterScoringFirstAway;
    }

    public double getWinsAfterConcedingFirstAtHome() {
        return winsAfterConcedingFirstAtHome;
    }

    public void setWinsAfterConcedingFirstAtHome(double winsAfterConcedingFirstAtHome) {
        this.winsAfterConcedingFirstAtHome = winsAfterConcedingFirstAtHome;
    }

    public double getWinsAfterConcedingFirstAway() {
        return winsAfterConcedingFirstAway;
    }

    public void setWinsAfterConcedingFirstAway(double winsAfterConcedingFirstAway) {
        this.winsAfterConcedingFirstAway = winsAfterConcedingFirstAway;
    }

    public double getLosesAfterConcedingFirstAtHome() {
        return losesAfterConcedingFirstAtHome;
    }

    public void setLosesAfterConcedingFirstAtHome(double losesAfterConcedingFirstAtHome) {
        this.losesAfterConcedingFirstAtHome = losesAfterConcedingFirstAtHome;
    }

    public double getLosesAfterConcedingFirstAway() {
        return losesAfterConcedingFirstAway;
    }

    public void setLosesAfterConcedingFirstAway(double losesAfterConcedingFirstAway) {
        this.losesAfterConcedingFirstAway = losesAfterConcedingFirstAway;
    }

    public double getDrawsAfterConcedingFirstAtHome() {
        return drawsAfterConcedingFirstAtHome;
    }

    public void setDrawsAfterConcedingFirstAtHome(double drawsAfterConcedingFirstAtHome) {
        this.drawsAfterConcedingFirstAtHome = drawsAfterConcedingFirstAtHome;
    }

    public double getDrawsAfterConcedingFirstAway() {
        return drawsAfterConcedingFirstAway;
    }

    public void setDrawsAfterConcedingFirstAway(double drawsAfterConcedingFirstAway) {
        this.drawsAfterConcedingFirstAway = drawsAfterConcedingFirstAway;
    }

    public TimeRange<Integer> getAvgTimeToScoreAfterConcedingAtHome() {
        return avgTimeToScoreAfterConcedingAtHome;
    }

    public void setAvgTimeToScoreAfterConcedingAtHome(TimeRange<Integer> avgTimeToScoreAfterConcedingAtHome) {
        this.avgTimeToScoreAfterConcedingAtHome = avgTimeToScoreAfterConcedingAtHome;
    }

    public TimeRange<Integer> getAvgTimeToScoreAfterConcedingAway() {
        return avgTimeToScoreAfterConcedingAway;
    }

    public void setAvgTimeToScoreAfterConcedingAway(TimeRange<Integer> avgTimeToScoreAfterConcedingAway) {
        this.avgTimeToScoreAfterConcedingAway = avgTimeToScoreAfterConcedingAway;
    }

    public double getConcedeAfterScoringPercentAtHome() {
        return concedeAfterScoringPercentAtHome;
    }

    public void setConcedeAfterScoringPercentAtHome(double concedeAfterScoringPercentAtHome) {
        this.concedeAfterScoringPercentAtHome = concedeAfterScoringPercentAtHome;
    }

    public double getConcedeAfterScoringPercentAway() {
        return concedeAfterScoringPercentAway;
    }

    public void setConcedeAfterScoringPercentAway(double concedeAfterScoringPercentAway) {
        this.concedeAfterScoringPercentAway = concedeAfterScoringPercentAway;
    }

    public double getScoresAfterConcedingPercentAtHome() {
        return scoresAfterConcedingPercentAtHome;
    }

    public void setScoresAfterConcedingPercentAtHome(double scoresAfterConcedingPercentAtHome) {
        this.scoresAfterConcedingPercentAtHome = scoresAfterConcedingPercentAtHome;
    }

    public double getScoresAfterConcedingPercentAway() {
        return scoresAfterConcedingPercentAway;
    }

    public void setScoresAfterConcedingPercentAway(double scoresAfterConcedingPercentAway) {
        this.scoresAfterConcedingPercentAway = scoresAfterConcedingPercentAway;
    }

    public double getDoublesAfterScoringPercentAtHome() {
        return doublesAfterScoringPercentAtHome;
    }

    public void setDoublesAfterScoringPercentAtHome(double doublesAfterScoringPercentAtHome) {
        this.doublesAfterScoringPercentAtHome = doublesAfterScoringPercentAtHome;
    }

    public double getDoublesAfterScoringPercentAway() {
        return doublesAfterScoringPercentAway;
    }

    public void setDoublesAfterScoringPercentAway(double doublesAfterScoringPercentAway) {
        this.doublesAfterScoringPercentAway = doublesAfterScoringPercentAway;
    }

    public double getConcedesAfterConcedingPercentAtHome() {
        return concedesAfterConcedingPercentAtHome;
    }

    public void setConcedesAfterConcedingPercentAtHome(double concedesAfterConcedingPercentAtHome) {
        this.concedesAfterConcedingPercentAtHome = concedesAfterConcedingPercentAtHome;
    }

    public double getConcedesAfterConcedingPercentAway() {
        return concedesAfterConcedingPercentAway;
    }

    public void setConcedesAfterConcedingPercentAway(double concedesAfterConcedingPercentAway) {
        this.concedesAfterConcedingPercentAway = concedesAfterConcedingPercentAway;
    }

    public double getDefendsAfterScoringFirstPercentAtHome() {
        return defendsAfterScoringFirstPercentAtHome;
    }

    public void setDefendsAfterScoringFirstPercentAtHome(double defendsAfterScoringFirstPercentAtHome) {
        this.defendsAfterScoringFirstPercentAtHome = defendsAfterScoringFirstPercentAtHome;
    }

    public double getDefendsAfterScoringFirstPercentAway() {
        return defendsAfterScoringFirstPercentAway;
    }

    public double getDefendsAfterConcedingFirstPercentAtHome() {
        return defendsAfterConcedingFirstPercentAtHome;
    }

    public void setDefendsAfterConcedingFirstPercentAtHome(double defendsAfterConcedingFirstPercentAtHome) {
        this.defendsAfterConcedingFirstPercentAtHome = defendsAfterConcedingFirstPercentAtHome;
    }

    public double getDefendsAfterConcedingFirstPercentAway() {
        return defendsAfterConcedingFirstPercentAway;
    }

    public void setDefendsAfterConcedingFirstPercentAway(double defendsAfterConcedingFirstPercentAway) {
        this.defendsAfterConcedingFirstPercentAway = defendsAfterConcedingFirstPercentAway;
    }

    public void setDefendsAfterScoringFirstPercentAway(double defendsAfterScoringFirstPercentAway) {
        this.defendsAfterScoringFirstPercentAway = defendsAfterScoringFirstPercentAway;
    }

    public double getDefendsWinningScorePercentAtHome() {
        return defendsWinningScorePercentAtHome;
    }

    public void setDefendsWinningScorePercentAtHome(double defendsWinningScorePercentAtHome) {
        this.defendsWinningScorePercentAtHome = defendsWinningScorePercentAtHome;
    }

    public double getDefendsWinningScorePercentAway() {
        return defendsWinningScorePercentAway;
    }

    public void setDefendsWinningScorePercentAway(double defendsWinningScorePercentAway) {
        this.defendsWinningScorePercentAway = defendsWinningScorePercentAway;
    }

    public TimeRange<Integer> getAvgTimeToConcedeAfterScoringAtHome() {
        return avgTimeToConcedeAfterScoringAtHome;
    }

    public void setAvgTimeToConcedeAfterScoringAtHome(TimeRange<Integer> avgTimeToConcedeAfterScoringAtHome) {
        this.avgTimeToConcedeAfterScoringAtHome = avgTimeToConcedeAfterScoringAtHome;
    }

    public TimeRange<Integer> getAvgTimeToConcedeAfterScoringAway() {
        return avgTimeToConcedeAfterScoringAway;
    }

    public void setAvgTimeToConcedeAfterScoringAway(TimeRange<Integer> avgTimeToConcedeAfterScoringAway) {
        this.avgTimeToConcedeAfterScoringAway = avgTimeToConcedeAfterScoringAway;
    }

    public int getHomeStatsSize() {
        return homeStatsSize;
    }

    public void setHomeStatsSize(int homeStatsSize) {
        this.homeStatsSize = homeStatsSize;
    }

    public int getAwayStatsSize() {
        return awayStatsSize;
    }

    public void setAwayStatsSize(int awayStatsSize) {
        this.awayStatsSize = awayStatsSize;
    }
}
