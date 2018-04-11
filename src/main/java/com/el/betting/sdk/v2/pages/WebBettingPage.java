package com.el.betting.sdk.v2.pages;

import com.el.betting.sdk.v2.provider.Bookmaker;
import org.springframework.data.annotation.PersistenceConstructor;

public class WebBettingPage extends BettingPage {

    private String marketID;

    @PersistenceConstructor
    public WebBettingPage(Bookmaker bookmaker, String marketID, String bettingUrl) {
        super(bookmaker, bettingUrl);
        this.marketID = marketID;
    }

    public WebBettingPage(Bookmaker bookmaker, String bettingUrl) {
        super(bookmaker, bettingUrl);
    }

    public WebBettingPage(Bookmaker bookmaker) {
        super(bookmaker);
    }

    @Deprecated
    public String getMarketID() {
        return marketID;
    }

    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }


    @Override
    public String toString() {
        return "WebBettingPage{" +
                "marketID='" + marketID + '\'' +
                "} " + super.toString();
    }
}
