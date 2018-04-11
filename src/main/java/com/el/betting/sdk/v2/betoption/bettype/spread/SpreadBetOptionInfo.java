package com.el.betting.sdk.v2.betoption.bettype.spread;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;

import java.math.BigDecimal;

public interface SpreadBetOptionInfo extends BetOptionInfo {

    BigDecimal getSpread();

    Team getTeam();
}
