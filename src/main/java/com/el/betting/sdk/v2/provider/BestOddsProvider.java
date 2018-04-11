package com.el.betting.sdk.v2.provider;


public enum BestOddsProvider implements GameOddsProvider {
    ODDSChecker("oddschecker", "oddschecker.com", 1),
    ODDSCheckerMetadataCrawler("oddschecker", "oddschecker.com", 1, "NON_JS"),
    BestBetting("BestBetting", "BestBetting.com", 1, "NON_JS"),
    ODDS24("odds24", "odds24.com", 1),
    ODDSCheckerTest("oddschcker", "oddschcker.com", 1),
    STAKE_ODDS("Stake Odds", "stakeodds.com/davbet/notfavourites", 1),
    STAKE_ODDS_SURE_BET("Stake Odds", "stakeodds.com/davbet/all", 1),
    STAKE_ODDS_LAY_BACK_SURE_BET("Stake Odds", "stakeodds.com/davbet/win-win", 1);

    private String name;
    private String url;
    private int confidence;
    private String profileStrategy;

    BestOddsProvider(String name, String url) {
        this(name, url, -1);
    }

    BestOddsProvider(String name, String url, int confidence) {
        this(name, url, confidence, "DEFAULT");
    }

    BestOddsProvider(String name, String url, int confidence, String profileStrategy) {
        this.name = name;
        this.url = url;
        this.confidence = confidence;
        this.profileStrategy = profileStrategy;
    }

    public String getName() {
        return name;
    }

    public String getProfileStrategy() {
        return profileStrategy;
    }
}
