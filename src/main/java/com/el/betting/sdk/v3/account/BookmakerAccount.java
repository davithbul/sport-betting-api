package com.el.betting.sdk.v3.account;

import com.el.betting.sdk.v2.Currency;
import com.el.betting.sdk.v2.provider.Bookmaker;

public class BookmakerAccount implements UserAccount {
    private final Bookmaker bookmaker;
    private final String email;
    private final String userName;
    private String password;
    private final Currency currency;

    public BookmakerAccount(Bookmaker bookmaker, String userName, String email, Currency currency, String password) {
        this.bookmaker = bookmaker;
        this.userName = userName;
        this.email = email;
        this.currency = currency;
        this.password = password;
    }

    public Bookmaker getBookmaker() {
        return bookmaker;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getEmail() {
        return email;
    }
}
