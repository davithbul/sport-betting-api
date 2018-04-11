package com.el.betting.sdk.v3.betoption.group;

import com.el.betting.sdk.v2.BetExchangeType;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.google.common.base.Preconditions;

import java.math.BigDecimal;

public class LayBackBetOptionGroupShare<T extends LayBackBetOption> extends BetOptionGroupShare<T>/*extends LayBackBetOptionGroup<T>*/ {

    /**
     * LayBetShare is the amount which user will get back after winning, it doesn't contain his stake.
     */
    private final BigDecimal[] betOptionsShare;
    /**
     * The amount which users bets.
     */
    private BigDecimal layUserBetShare;
    /**
     * The full amount which user will get after winning lay bet (including also his own stake)
     */
    private BigDecimal liabilityShare;
    protected final int backBetOptionNumber;
    protected final int layBetOptionNumber;


    public LayBackBetOptionGroupShare(LayBackBetOptionGroup<T> betOptionGroup) {
        this(betOptionGroup.getBackBetOption(), betOptionGroup.getLayBetOption());
    }

    public LayBackBetOptionGroupShare(T backBetOption, T layBetOption) {
        super(backBetOption, layBetOption);
        betOptionsShare = new BigDecimal[2];
        Preconditions.checkArgument(backBetOption.getBetExchangeType() == BetExchangeType.BACK);
        Preconditions.checkArgument(layBetOption.getBetExchangeType() == BetExchangeType.LAY);
        backBetOptionNumber = 1;
        layBetOptionNumber = 2;
    }

    public LayBackBetOptionGroupShare(T backBetOption, BigDecimal backBetOptionShare, T layBetOption, BigDecimal layBetOptionShare) {
        super(backBetOption, layBetOption);
        betOptionsShare = new BigDecimal[]{backBetOptionShare, layBetOptionShare};
        Preconditions.checkArgument(backBetOption.getBetExchangeType() == BetExchangeType.BACK);
        Preconditions.checkArgument(layBetOption.getBetExchangeType() == BetExchangeType.LAY);
        backBetOptionNumber = 1;
        layBetOptionNumber = 2;
    }

    public void setBetOptionShare(int betOptionNumber, T betOption, BigDecimal share) {
        Preconditions.checkArgument(betOptionNumber > 0 && betOptionNumber <= getOptionCount(), "BetOption number should be between 1 to " + getOptionCount());
        setBetOption(betOptionNumber, betOption);
        betOptionsShare[betOptionNumber - 1] = share;
    }

    public T getBackBetOption() {
        return getBetOption(backBetOptionNumber);
    }

    public T getLayBetOption() {
        return getBetOption(layBetOptionNumber);
    }


    public BigDecimal getBetShare(int betOptionNumber) {
        Preconditions.checkArgument(betOptionNumber > 0 && betOptionNumber <= getOptionCount(), "BetOption number should be between 1 to " + getOptionCount());
        return betOptionsShare[betOptionNumber - 1];
    }

    public BigDecimal getBackBetShare() {
        return getBetShare(backBetOptionNumber);
    }

    public BigDecimal getLayBetShare() {
        return getBetShare(layBetOptionNumber);
    }

    public void setBackBetShare(BigDecimal backBetShare) {
        betOptionsShare[backBetOptionNumber - 1] = backBetShare;
    }

    public void setLayBetShare(BigDecimal layBetShare) {
        betOptionsShare[layBetOptionNumber - 1] = layBetShare;
    }

    public void setLayUserBetShare(BigDecimal layUserBetShare) {
        this.layUserBetShare = layUserBetShare;
    }

    public void setLiabilityShare(BigDecimal liabilityShare) {
        this.liabilityShare = liabilityShare;
    }

    public BigDecimal getLayUserBetShare() {
        return layUserBetShare;
    }

    public BigDecimal getLiabilityShare() {
        return liabilityShare;
    }
}
