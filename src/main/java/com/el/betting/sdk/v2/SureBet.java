package com.el.betting.sdk.v2;

import com.el.betting.sdk.v3.betoption.group.BetOptionGroup;
import com.el.betting.sdk.v3.betoption.group.BetOptionGroupShare;

import java.math.BigDecimal;

public class SureBet {
    private Sport sport;
    private BetOptionGroup betOptionGroup;
    private BetOptionGroupShare betOptionGroupShare;
    private BigDecimal winPercentile;

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public BetOptionGroup getBetOptionGroup() {
        return betOptionGroup;
    }

    public void setBetOptionGroup(BetOptionGroup betOptionGroup) {
        this.betOptionGroup = betOptionGroup;
    }

    public BetOptionGroupShare getBetOptionGroupShare() {
        return betOptionGroupShare;
    }

    public void setBetOptionGroupShare(BetOptionGroupShare betOptionGroup) {
        this.betOptionGroupShare = betOptionGroupShare;
    }

    public BigDecimal getWinPercentile() {
        return winPercentile;
    }

    public void setWinPercentile(BigDecimal winPercentile) {
        this.winPercentile = winPercentile;
    }
}
