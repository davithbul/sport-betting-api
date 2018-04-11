package com.el.betting.sdk.v2.pages;

import com.el.betting.sdk.v2.provider.Bookmaker;
import org.springframework.data.annotation.PersistenceConstructor;

import java.io.Serializable;

public abstract class BettingPage implements Serializable {

    private Bookmaker bookmaker;

    private String bettingUrl;

    public BettingPage(Bookmaker bookmaker, String bettingUrl) {
        this.bookmaker = bookmaker;
        this.bettingUrl = bettingUrl;
    }

    @PersistenceConstructor
    public BettingPage(Bookmaker bookmaker) {
        this.bookmaker = bookmaker;
    }

    public Bookmaker getBookmaker() {
        return bookmaker;
    }

    public String getBettingUrl() {
        return bettingUrl;
    }

    @Override
    public String toString() {
        return "BettingPage{" +
                "bookmaker=" + bookmaker +
                ", bettingUrl='" + bettingUrl + '\'' +
                '}';
    }
}
