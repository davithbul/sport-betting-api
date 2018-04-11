package com.el.betting.sdk.v3.trading;

/**
 * Represents performance of trade rule.
 * It can contain cumulative performance for multiple days of work
 * of tradeRule.
 */
public class TradeRulePerformance {
    private String id;
    private int datasetSize;
    private double totalProfit;
    private double highestProfit;
    private double worstLose;
    private double mean;
    private int matchedTradeCount;
    private TradeRule tradeRule;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDatasetSize() {
        return datasetSize;
    }

    public void setDatasetSize(int datasetSize) {
        this.datasetSize = datasetSize;
    }

    public TradeRule getTradeRule() {
        return tradeRule;
    }

    public void setTradeRule(TradeRule tradeRule) {
        this.tradeRule = tradeRule;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getHighestProfit() {
        return highestProfit;
    }

    public void setHighestProfit(double highestProfit) {
        this.highestProfit = highestProfit;
    }

    public double getWorstLose() {
        return worstLose;
    }

    public void setWorstLose(double worstLose) {
        this.worstLose = worstLose;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public int getMatchedTradeCount() {
        return matchedTradeCount;
    }

    public void setMatchedTradeCount(int matchedTradeCount) {
        this.matchedTradeCount = matchedTradeCount;
    }
}
