package com.el.betting.utils;

import com.el.betting.sdk.v2.BetExchangeType;
import com.el.betting.sdk.v2.Event;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.TotalType;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;

import java.math.BigDecimal;
import java.util.Optional;

public class TotalPointsUtils {

    public static BigDecimal getBackPrice(Event<? extends Participant> event, double points, TotalType totalType) {
        final Optional<LayBackBetOption> totalPointsBetOption = LayBackBetUtils.getTotalPointsBetOption(event, points, BetExchangeType.BACK, totalType);
        if (!totalPointsBetOption.isPresent()) {
            return BigDecimal.ONE;
        }
        return totalPointsBetOption.get().getPrice();
    }

    public static BigDecimal getLayPrice(Event<? extends Participant> event, double points, TotalType totalType) {
        final Optional<LayBackBetOption> totalPointsBetOption = LayBackBetUtils.getTotalPointsBetOption(event, points, BetExchangeType.LAY, totalType);
        if (!totalPointsBetOption.isPresent()) {
            return BigDecimal.valueOf(100000);
        }
        return totalPointsBetOption.get().getPrice();
    }
}
