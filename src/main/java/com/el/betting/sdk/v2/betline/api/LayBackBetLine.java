package com.el.betting.sdk.v2.betline.api;

import com.el.betting.sdk.v2.MarketStatus;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOptionInfo;

/**
 * Represents wrapper of real betLineInfo.
 */
public abstract class LayBackBetLine<T extends LayBackBetOption, L extends BetLineInfo> extends BetLine<LayBackBetOption, LayBackBetOptionInfo> {

    private L betLineInfo;

    public LayBackBetLine(L betLineInfo, MarketStatus marketStatus) {
        super(betLineInfo, marketStatus);
        this.betLineInfo = betLineInfo;
    }

    public L getBetLineInfo() {
        return betLineInfo;
    }
}
