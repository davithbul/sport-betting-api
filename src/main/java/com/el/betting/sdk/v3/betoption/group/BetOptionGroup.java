package com.el.betting.sdk.v3.betoption.group;

import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.google.common.base.Preconditions;

import java.math.BigDecimal;

/**
 * BetOptionGroup is domain object of stakeodds, which contains any
 * list of bet Options. It's intermediate object and neither saved in the
 * database not read from external bet option providers directly.
 */
public class BetOptionGroup<T extends BetOption> extends BetOptionInfoGroup<T> {

    private final T[] betOptions;

    @SafeVarargs
    public BetOptionGroup(T... betOptions) {
        super(betOptions);
//        Preconditions.checkArgument(betOptions.length >= 1);
        this.betOptions = betOptions;
    }

    public void setBetOption(int betOptionNumber, T betOption) {
        Preconditions.checkArgument(betOptionNumber <= betOptions.length && betOptionNumber > 0, "BetOption number should be between 1 to 3, " + betOptionNumber);
        betOptions[betOptionNumber - 1] = betOption;
    }

    public BigDecimal getPrice(int betOptionNumber) {
        Preconditions.checkArgument(betOptionNumber <= betOptions.length && betOptionNumber > 0, "BetOption number should be between 1 to 3, " + betOptionNumber);
        return betOptions[betOptionNumber - 1].getPrice();
    }

    public T getBetOption(int betOptionNumber) {
        Preconditions.checkArgument(betOptionNumber <= betOptions.length && betOptionNumber > 0, "BetOption number should be between 1 to 3, " + betOptionNumber);
        return betOptions[betOptionNumber - 1];
    }

    public BettingPage getBettingPage(int betOptionNumber) {
        Preconditions.checkArgument(betOptionNumber <= betOptions.length && betOptionNumber > 0, "BetOption number should be between 1 to 3, " + betOptionNumber);
        return betOptions[betOptionNumber - 1].getBettingPage();
    }

    public int getOptionCount() {
        return betOptions.length;
    }

    public T[] getBetOptions() {
        return betOptions;
    }
}
