package com.el.betting.sdk.v3.trading;

import java.util.Optional;
import java.util.function.Function;

public class OddsPrecisionBettingRuleBuilder extends TotalPointsBettingRuleBuilder<OddsPrecisionBettingRuleBuilder> {

    private Function<FootballTradeContext, Optional<Double>> pricePredictorFunction;

    public OddsPrecisionBettingRuleBuilder setPricePredictorFunction(Function<FootballTradeContext, Optional<Double>> pricePredictorFunction) {
        this.pricePredictorFunction = pricePredictorFunction;
        return this;
    }

    public OddsPrecisionBettingRule createOddsPrecisionBettingRule() {
        final OddsPrecisionBettingRule oddsPrecisionBettingRule = new OddsPrecisionBettingRule(description, order, points, totalType, backBetStartTime, backBetEndTime, backStake, maxLayPrice, layBetStartTime, layBetEndTime, minBackPrice, backBetAllowedDiff, layBetAllowedDiff, eventCondition, backBetCondition, layBetCondition);
        oddsPrecisionBettingRule.setPricePredictorFunction(pricePredictorFunction);
        return oddsPrecisionBettingRule;
    }
}