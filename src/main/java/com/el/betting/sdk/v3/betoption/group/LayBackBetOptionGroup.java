package com.el.betting.sdk.v3.betoption.group;

import com.el.betting.sdk.v2.BetExchangeType;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.google.common.base.Preconditions;

public class LayBackBetOptionGroup<T extends LayBackBetOption> extends BetOptionGroup<T> {

    protected int backBetOptionNumber;
    protected int layBetOptionNumber;

    public LayBackBetOptionGroup(T backBetOption, T layBetOption) {
        super(backBetOption, layBetOption);
        Preconditions.checkArgument(backBetOption.getBetExchangeType() == BetExchangeType.BACK);
        Preconditions.checkArgument(layBetOption.getBetExchangeType() == BetExchangeType.LAY);
        backBetOptionNumber = 1;
        layBetOptionNumber = 2;
    }

    public T getBackBetOption() {
        return getBetOption(backBetOptionNumber);
    }

    public T getLayBetOption() {
        return getBetOption(layBetOptionNumber);
    }
}
