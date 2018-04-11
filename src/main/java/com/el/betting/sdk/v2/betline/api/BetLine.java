package com.el.betting.sdk.v2.betline.api;


import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * BetLine represents game options line for the event for the given period.
 * This is like a group of bet options / possible bets for the same event
 * in the same time with opposite or partially overlapping (e.g. double chance) outcomes.
 * It's possible to get each possible bet option by calling BetLine#getBetOptions.
 * In other words, each BetLine contains betOptions which are part of the same line (i.e. lineID)
 * <p>
 * E.G.
 * In Real vs Barca game, the money line game lines might be considered
 * Match Winner - Real - odd1 vs Draw - odd2 vs Barca - odd3 (bet line1)
 * First half Winner - Real - odd1 vs Draw - odd2 vs Barca - odd3 (bet line2)
 *
 * For different type of game lines the representation might be different
 */
public abstract class BetLine<T extends BetOption, M extends BetOptionInfo> extends BetLineInfo<M> implements Serializable {

    private MarketStatus marketStatus;

    public BetLine(BetLineInfo betLineInfo, MarketStatus marketStatus) {
        super(betLineInfo);
        this.marketStatus = marketStatus;
    }

    public BetLine(BetLine betLine) {
        super(betLine);
        this.marketStatus = betLine.getMarketStatus();
    }

    public BetLine(long eventID, String lineID, Period period, BetType betType, MarketStatus marketStatus, Stake maxStake, OddsFormat oddsFormat, LocalDateTime startTime, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, betType, maxStake, oddsFormat, startTime, additionalProperties);
        this.marketStatus = marketStatus;
    }

    @Override
    public List<M> getBetOptionsInfoList() {
        List<T> betOptions = getBetOptions();
        return betOptions.stream()
                .map(betOption -> (M)betOption)
                .collect(Collectors.toList());
    }

    public void setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
    }

    public MarketStatus getMarketStatus() {
        return marketStatus;
    }

    @JsonIgnore
    public abstract List<T> getBetOptions();

    @Override
    public String toString() {
        return "BetLine{" +
                "marketStatus=" + marketStatus +
                "} " + super.toString();
    }
}
