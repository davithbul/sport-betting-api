package com.el.betting.sdk.v2;

import org.springframework.data.annotation.PersistenceConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

public class BetPrice implements Serializable {
    private BigDecimal price;
    private BigDecimal size;
    private Currency currency;

    public BetPrice(BigDecimal price, BigDecimal size) {
        this.price = price;
        this.size = size;
    }

    @PersistenceConstructor
    public BetPrice(BigDecimal price, BigDecimal size, Currency currency) {
        this.price = price;
        this.size = size;
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getSize() {
        return size;
    }

    public Currency getCurrency() {
        return currency;
    }
}
