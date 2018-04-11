package com.el.betting.sdk.v3.trading;

import com.el.betting.sdk.v2.TotalType;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class OddsPrecisionBettingRule extends TotalPointsBettingRule {
    private Function<FootballTradeContext, Optional<Double>> pricePredictorFunction;

    protected OddsPrecisionBettingRule(String description, Order order, double points, TotalType totalType, int backBetStartTime, int backBetEndTime, BigDecimal backStake, BigDecimal maxLayPrice, int layBetStartTime, int layBetEndTime, BigDecimal minBackPrice, BigDecimal backBetAllowedDiff, BigDecimal layBetAllowedDiff, Predicate<StatefulFootballTradeContext> eventCondition, Predicate<StatefulFootballTradeContext> backBetCondition, Predicate<StatefulFootballTradeContext> layBetCondition) {
        super(description, order, points, totalType, backBetStartTime, backBetEndTime, backStake, maxLayPrice, layBetStartTime, layBetEndTime, minBackPrice, backBetAllowedDiff, layBetAllowedDiff, eventCondition, backBetCondition, layBetCondition);
    }

    public void setPricePredictorFunction(Function<FootballTradeContext, Optional<Double>> pricePredictorFunction) {
        this.pricePredictorFunction = pricePredictorFunction;
    }

    public Optional<Double> getPredictedPrice(FootballTradeContext tradeContext) {
        return pricePredictorFunction.apply(tradeContext);
    }
}
