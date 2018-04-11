package com.el.betting.sdk.v3.trading;

import com.el.betting.sdk.v2.BetType;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;

public class TradeRule implements Serializable {

    @Indexed
    private BetType betType;

    public TradeRule(BetType betType) {
        this.betType = betType;
    }

    public BetType getBetType() {
        return betType;
    }
}
