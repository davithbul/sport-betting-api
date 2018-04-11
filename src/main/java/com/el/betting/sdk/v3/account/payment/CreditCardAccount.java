package com.el.betting.sdk.v3.account.payment;

import com.el.betting.sdk.v2.Currency;
import com.el.betting.sdk.v3.account.WalletAccount;

import javax.annotation.concurrent.Immutable;

@Immutable
public class CreditCardAccount extends WalletAccount {
    private final String name;
    private final long cardNumber;
    private final int secureCode;
    private final int expirationYear;
    private final int expirationMonth;

    public CreditCardAccount(String name, Currency currency, long cardNumber, int secureCode, int expirationYear, int expirationMonth) {
        super(PaymentSystem.CREDIT_CARD, name, currency);
        this.name = name;
        this.cardNumber = cardNumber;
        this.secureCode = secureCode;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
    }

    public String getName() {
        return name;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public int getSecureCode() {
        return secureCode;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }
}
