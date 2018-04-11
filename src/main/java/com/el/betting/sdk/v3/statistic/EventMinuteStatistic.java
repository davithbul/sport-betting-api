package com.el.betting.sdk.v3.statistic;

public class EventMinuteStatistic {
    private int minute;
    private double price;
    private double matchedAmount;
    private Integer goalCount;
    private Boolean goalScored;

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMatchedAmount() {
        return matchedAmount;
    }

    public void setMatchedAmount(double matchedAmount) {
        this.matchedAmount = matchedAmount;
    }

    public Integer getGoalCount() {
        return goalCount;
    }

    public void setGoalCount(Integer goalCount) {
        this.goalCount = goalCount;
    }

    public Boolean getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(Boolean goalScored) {
        this.goalScored = goalScored;
    }
}
