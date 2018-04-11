package com.el.betting.sdk.v3.account;

import com.el.betting.sdk.v2.Currency;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.annotation.concurrent.Immutable;
import java.math.BigDecimal;

@Immutable
public class Money {
    private Currency currency;
    private BigDecimal amount;

    public Money(int amount, Currency currency) {
        this(BigDecimal.valueOf(amount), currency);
    }

    @PersistenceConstructor
    public Money(BigDecimal amount, Currency currency) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
