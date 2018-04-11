package com.el.betting.sdk.v2.betoption.bettype.teampoints;

import com.el.betting.sdk.v2.Team;
import com.el.betting.sdk.v2.TotalType;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;

import java.math.BigDecimal;

public interface TeamPointsBetOptionInfo extends BetOptionInfo {

    TotalType getTotalType();

    Team getTeam();

    double getPoints();
}
