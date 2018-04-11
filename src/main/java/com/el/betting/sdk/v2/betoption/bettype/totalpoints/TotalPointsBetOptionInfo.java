package com.el.betting.sdk.v2.betoption.bettype.totalpoints;

import com.el.betting.sdk.v2.TotalType;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;

import java.math.BigDecimal;

public interface TotalPointsBetOptionInfo extends BetOptionInfo {

    TotalType getTotalType();

    double getPoints();
}
