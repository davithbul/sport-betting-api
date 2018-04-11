package com.el.betting.sdk.v3.account.payment;


import com.el.betting.sdk.v2.Currency;
import com.el.betting.sdk.v3.account.WalletAccount;

import javax.annotation.concurrent.Immutable;

@Immutable
public class NetellerAccount extends WalletAccount {
    private final String email;
    private final String password;
    private final long secureCode;

    public NetellerAccount(long accountNumber, Currency currency, String email, long secureCode, String password) {
        super(PaymentSystem.NETELLER, String.valueOf(accountNumber), currency);
        this.email = email;
        this.secureCode = secureCode;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getSecureCode() {
        return secureCode;
    }
}
