package com.el.betting.sdk.v3.betoption.group;

import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.google.common.base.Preconditions;

import java.math.BigDecimal;

public class BetOptionGroupShare<T extends BetOption> extends BetOptionGroup<T> {

    private final BigDecimal[] betOptionsShare;

    public BetOptionGroupShare(BetOptionGroup betOptionGroup) {
        this((T[]) betOptionGroup.getBetOptions());
    }

    public BetOptionGroupShare(T... betOptions) {
        this(betOptions, new BigDecimal[betOptions.length]);
    }

    public BetOptionGroupShare(T[] betOptions, BigDecimal[] betOptionsShare) {
        super(betOptions);
        Preconditions.checkArgument(betOptions.length == betOptionsShare.length);
        this.betOptionsShare = betOptionsShare;
    }

    public void setBetOptionShare(int betOptionNumber, T betOption, BigDecimal share) {
        Preconditions.checkArgument(betOptionNumber > 0 && betOptionNumber <= getOptionCount(), "BetOption number should be between 1 to " + getOptionCount());
        setBetOption(betOptionNumber, betOption);
        betOptionsShare[betOptionNumber - 1] = share;
    }

    public BigDecimal getBetShare(int betOptionNumber) {
        Preconditions.checkArgument(betOptionNumber > 0 && betOptionNumber <= getOptionCount(), "BetOption number should be between 1 to " + getOptionCount());
        return betOptionsShare[betOptionNumber - 1];
    }
}
