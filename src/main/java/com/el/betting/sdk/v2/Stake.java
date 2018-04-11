package com.el.betting.sdk.v2;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.math.BigDecimal;

@Immutable
public class Stake implements Serializable {

    private final BigDecimal amount;
    private final Currency currency;

    @PersistenceConstructor
    public Stake(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Stake add(BigDecimal amount) {
        return new Stake(this.amount.add(amount), currency);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stake)) return false;

        Stake stake1 = (Stake) o;

        if (currency != null ? !currency.equals(stake1.currency) : stake1.currency != null) return false;
        return amount.equals(stake1.amount);

    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return amount + " " + (currency != null ? currency.getName() : "");
    }
}
