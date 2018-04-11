package com.el.betting.sdk.v2.builders;

import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v2.betoption.api.Bet;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.DefaultMoneyLineBet;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.MoneyLineBetOption;
import com.el.betting.sdk.v2.betoption.bettype.teampoints.TeamPointsBet;
import com.el.betting.sdk.v2.betoption.bettype.teampoints.TeamPointsBetOption;
import com.el.betting.sdk.v2.betoption.bettype.totalpoints.TotalPointsBet;
import com.el.betting.sdk.v2.betoption.bettype.totalpoints.TotalPointsBetOption;

public class BetBuilder {

    public static Bet buildBet(BetOption betOption, Stake stake) {
        Bet bet;
        if(betOption instanceof TotalPointsBetOption) {
            bet = new TotalPointsBet((TotalPointsBetOption) betOption, stake);
        } else if(betOption instanceof MoneyLineBetOption) {
            bet = new DefaultMoneyLineBet((MoneyLineBetOption) betOption, stake);
        } else if(betOption instanceof TeamPointsBetOption) {
            bet = new TeamPointsBet((TeamPointsBetOption) betOption, stake);
        } else {
            throw new IllegalArgumentException("Can't find BetBuilder for betOption " + betOption);
        }

        return bet;
    }
}
