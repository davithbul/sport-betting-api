package com.el.betting.sdk.v2.criteria;

public class FeedUpdateTime implements FeedCriterion {

    private long milliseconds;

    public static FeedUpdateTime of(long milliseconds) {
        return new FeedUpdateTime(milliseconds);
    }

    public FeedUpdateTime(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}
