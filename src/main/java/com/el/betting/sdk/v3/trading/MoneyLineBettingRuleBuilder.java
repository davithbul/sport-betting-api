package com.el.betting.sdk.v3.trading;

import com.el.betting.sdk.v2.Team;

import java.math.BigDecimal;
import java.util.function.Predicate;

import static com.el.betting.common.Predicates.alwaysTrue;

public class MoneyLineBettingRuleBuilder {
    private String description = "";
    private BackLayBettingRule.Order order = BackLayBettingRule.Order.LAY_THEN_BACK;
    private Team.Side side;
    private int backBetStartTime;
    private int backBetEndTime;
    private BigDecimal backStake;
    private int layBetStartTime;
    private int layBetEndTime;
    private BigDecimal minBackPrice;
    private BigDecimal maxLayPrice;
    private BigDecimal backBetAllowedDiff;
    private BigDecimal layBetAllowedDiff;
    private Predicate<StatefulFootballTradeContext> eventCondition = alwaysTrue();
    private Predicate<StatefulFootballTradeContext> backBetCondition = alwaysTrue();
    private Predicate<StatefulFootballTradeContext> layBetCondition = alwaysTrue();

    public MoneyLineBettingRuleBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public MoneyLineBettingRuleBuilder setOrder(BackLayBettingRule.Order order) {
        this.order = order;
        return this;
    }

    public MoneyLineBettingRuleBuilder setSide(Team.Side side) {
        this.side = side;
        return this;
    }

    public MoneyLineBettingRuleBuilder setBackBetStartTime(int backBetStartTime) {
        this.backBetStartTime = backBetStartTime;
        return this;
    }

    public MoneyLineBettingRuleBuilder setBackBetEndTime(int backBetEndTime) {
        this.backBetEndTime = backBetEndTime;
        return this;
    }

    public MoneyLineBettingRuleBuilder setBackStake(BigDecimal backStake) {
        this.backStake = backStake;
        return this;
    }

    public MoneyLineBettingRuleBuilder setLayBetStartTime(int layBetStartTime) {
        this.layBetStartTime = layBetStartTime;
        return this;
    }

    public MoneyLineBettingRuleBuilder setLayBetEndTime(int layBetEndTime) {
        this.layBetEndTime = layBetEndTime;
        return this;
    }

    public MoneyLineBettingRuleBuilder setMinBackPrice(BigDecimal minBackPrice) {
        this.minBackPrice = minBackPrice;
        return this;
    }

    public MoneyLineBettingRuleBuilder setMaxLayPrice(BigDecimal maxLayPrice) {
        this.maxLayPrice = maxLayPrice;
        return this;
    }

    public MoneyLineBettingRuleBuilder setBackBetAllowedDiff(BigDecimal backBetAllowedDiff) {
        this.backBetAllowedDiff = backBetAllowedDiff;
        return this;
    }

    public MoneyLineBettingRuleBuilder setLayBetAllowedDiff(BigDecimal layBetAllowedDiff) {
        this.layBetAllowedDiff = layBetAllowedDiff;
        return this;
    }

    public MoneyLineBettingRuleBuilder setEventCondition(Predicate<StatefulFootballTradeContext> eventCondition) {
        this.eventCondition = eventCondition;
        return this;
    }

    public MoneyLineBettingRuleBuilder setBackBetCondition(Predicate<StatefulFootballTradeContext> backBetCondition) {
        this.backBetCondition = backBetCondition;
        return this;
    }

    public MoneyLineBettingRuleBuilder setLayBetCondition(Predicate<StatefulFootballTradeContext> layBetCondition) {
        this.layBetCondition = layBetCondition;
        return this;
    }

    public MoneyLineBettingRule createMoneyLineBettingRule() {
        return new MoneyLineBettingRule(description, order, side, backBetStartTime, backBetEndTime, backStake, layBetStartTime, layBetEndTime, minBackPrice, maxLayPrice, backBetAllowedDiff, layBetAllowedDiff, eventCondition, backBetCondition, layBetCondition);
    }
}