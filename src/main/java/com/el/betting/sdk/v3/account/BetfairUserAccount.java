package com.el.betting.sdk.v3.account;

import com.el.betting.sdk.v2.Currency;
import com.el.betting.sdk.v2.provider.Bookmaker;

public class BetfairUserAccount extends BookmakerAccount {

    private final String appName;
    private final String appKey;

    public BetfairUserAccount(Bookmaker bookmaker, String email, Currency currency, String appName, String appKey, String password) {
        super(bookmaker, email, email, currency, password);
        this.appName = appName;
        this.appKey = appKey;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppKey() {
        return appKey;
    }
}
