package com.el.betting.sdk.v3.account;

import com.el.betting.sdk.v2.Currency;
import com.el.betting.sdk.v3.account.payment.PaymentSystem;

import javax.annotation.concurrent.Immutable;

/**
 * Represents wallet account with valid credentials for a specific user.
 */
@Immutable
public abstract class WalletAccount implements UserAccount {
    private final PaymentSystem paymentSystem;
    /** This represents account number or email, if it's the identifier **/
    private final String account;
    private final Currency currency;

    protected WalletAccount(PaymentSystem paymentSystem, String account, Currency currency) {
        this.paymentSystem = paymentSystem;
        this.account = account;
        this.currency = currency;
    }

    public PaymentSystem getPaymentSystem() {
        return paymentSystem;
    }

    public String getAccount() {
        return account;
    }

    public Currency getCurrency() {
        return currency;
    }
}
