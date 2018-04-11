package com.el.betting.sdk.v3.betoption.group;

import com.el.betting.sdk.v2.BetExchangeType;
import com.el.betting.sdk.v2.betoption.api.Bet;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBet;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.google.common.base.Preconditions;

public class LayBackBetGroup extends BetGroup<LayBackBet, LayBackBetOption> {

    protected int backBetOptionNumber;
    protected int layBetOptionNumber;

    public LayBackBetGroup(LayBackBet backBet, LayBackBet layBet) {
        super(backBet, layBet);
        Preconditions.checkArgument(backBet.getBetExchangeType() == BetExchangeType.BACK);
        Preconditions.checkArgument(layBet.getBetExchangeType() == BetExchangeType.LAY);
        backBetOptionNumber = 1;
        layBetOptionNumber = 2;
    }

    public LayBackBet getBackBet() {
        return getBet(backBetOptionNumber);
    }

    public LayBackBet getLayBet() {
        return (LayBackBet) getBetOption(layBetOptionNumber);
    }
}
