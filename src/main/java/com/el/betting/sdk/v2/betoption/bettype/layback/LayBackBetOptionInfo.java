package com.el.betting.sdk.v2.betoption.bettype.layback;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;


/**
 * LayBetBetOptionInfo is a lay back wrapper of real betOption.
 * @param <T> is a type of real betOptionInfo which LayBackBetOptionInfo wraps.
 */
public class LayBackBetOptionInfo<T extends BetOptionInfo> extends AbstractBetOptionInfo implements BetOptionInfo {

    private final T betOptionInfo;
    private final BetExchangeType betExchangeType;

    public LayBackBetOptionInfo(T betOptionInfo, BetExchangeType betExchangeType) {
        super(betOptionInfo);
        this.betOptionInfo = betOptionInfo;
        this.betExchangeType = betExchangeType;
    }

    public T getBetOptionInfo() {
        return betOptionInfo;
    }

    public BetExchangeType getBetExchangeType() {
        return betExchangeType;
    }

    @Override
    public BetType getBetType() {
        return betOptionInfo.getBetType();
    }

    @Override
    public String toString() {
        return "LayBackBetOptionInfo{" +
                "eventId=" + betOptionInfo.getEvent() +
                "lineId=" + betOptionInfo.getLineID() +
                "selectionId=" + betOptionInfo.getSelectionID() +
                ", betExchangeType=" + betExchangeType +
                "} ";
    }
}

