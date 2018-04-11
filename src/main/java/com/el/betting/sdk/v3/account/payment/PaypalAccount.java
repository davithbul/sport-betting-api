package com.el.betting.sdk.v3.account.payment;

import com.el.betting.sdk.v2.Currency;
import com.el.betting.sdk.v3.account.WalletAccount;

import javax.annotation.concurrent.Immutable;

@Immutable
public class PaypalAccount extends WalletAccount {
    private final String email;
    private final String password;

    public PaypalAccount(String email, Currency currency, String password) {
        super(PaymentSystem.PAYPAL, email, currency);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
