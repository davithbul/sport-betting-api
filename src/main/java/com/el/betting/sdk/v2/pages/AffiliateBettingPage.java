package com.el.betting.sdk.v2.pages;

import com.el.betting.sdk.v2.market.AffiliateStrategy;
import com.el.betting.sdk.v2.provider.Bookmaker;
import org.springframework.data.annotation.PersistenceConstructor;

public class AffiliateBettingPage extends BettingPage {

    private AffiliateStrategy affiliateStrategy;


    public AffiliateBettingPage(Bookmaker bookmaker) {
        this(bookmaker, null);
    }

    @PersistenceConstructor
    public AffiliateBettingPage(Bookmaker bookmaker, String bettingUrl) {
        super(bookmaker, bettingUrl);
        this.affiliateStrategy = bookmaker.getAffiliateStrategy();
    }

    public AffiliateStrategy getAffiliateStrategy() {
        return affiliateStrategy;
    }

    @Override
    public String toString() {
        return "AffiliateBettingPage{" +
                "affiliateStrategy=" + affiliateStrategy +
                "} " + super.toString();
    }
}
