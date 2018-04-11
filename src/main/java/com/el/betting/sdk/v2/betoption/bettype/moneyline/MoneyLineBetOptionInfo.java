package com.el.betting.sdk.v2.betoption.bettype.moneyline;

import com.el.betting.sdk.v2.Team;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;


public interface MoneyLineBetOptionInfo extends BetOptionInfo {
    Team getTeam();
}
