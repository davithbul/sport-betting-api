package com.el.betting.sdk.v3.trading;

import com.el.betting.common.Predicates;
import com.el.betting.sdk.v2.TotalType;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class TotalPointsBettingRuleBuilder<T extends TotalPointsBettingRuleBuilder> {
    protected String description = "";
    protected BackLayBettingRule.Order order = BackLayBettingRule.Order.BACK_THEN_LAY;
    protected TotalType totalType;
    protected double points;
    protected int backBetStartTime;
    protected int backBetEndTime;
    protected int layBetStartTime;
    protected BigDecimal backStake;
    protected int layBetEndTime;
    protected BigDecimal minBackPrice;
    protected BigDecimal maxLayPrice;
    protected BigDecimal backBetAllowedDiff;
    protected BigDecimal layBetAllowedDiff;
    protected Predicate<StatefulFootballTradeContext> eventCondition = Predicates.alwaysTrue();
    protected Predicate<StatefulFootballTradeContext> backBetCondition = Predicates.alwaysTrue();
    protected Predicate<StatefulFootballTradeContext> layBetCondition = Predicates.alwaysTrue();

    public T setOrder(BackLayBettingRule.Order order) {
        this.order = order;
        return (T) this;
    }

    public T setPoints(double points) {
        this.points = points;
        return (T) this;
    }

    public T setTotalType(TotalType totalType) {
        this.totalType = totalType;
        return (T) this;
    }

    public T setBackBetStartTime(int backBetStartTime) {
        this.backBetStartTime = backBetStartTime;
        return (T) this;
    }

    public T setBackBetEndTime(int backBetEndTime) {
        this.backBetEndTime = backBetEndTime;
        return (T) this;
    }

    public T setBackStake(BigDecimal backStake) {
        this.backStake =  backStake;
        return (T) this;
    }

    public T setLayBetStartTime(int layBetStartTime) {
        this.layBetStartTime = layBetStartTime;
        return (T) this;
    }

    public T setLayBetEndTime(int layBetEndTime) {
        this.layBetEndTime = layBetEndTime;
        return (T) this;
    }

    public T setMinBackPrice(BigDecimal minBackPrice) {
        this.minBackPrice = minBackPrice;
        return (T) this;
    }

    public T setMaxLayPrice(BigDecimal maxLayPrice) {
        this.maxLayPrice = maxLayPrice;
        return (T) this;
    }

    public T setBackBetAllowedDiff(BigDecimal backBetAllowedDiff) {
        this.backBetAllowedDiff = backBetAllowedDiff;
        return (T) this;
    }

    public T setLayBetAllowedDiff(BigDecimal layBetAllowedDiff) {
        this.layBetAllowedDiff = layBetAllowedDiff;
        return (T) this;
    }

    public T setDescription(String description) {
        this.description = description;
        return (T) this;
    }

    public T setBackBetCondition(Predicate<StatefulFootballTradeContext> backBetCondition) {
        this.backBetCondition = backBetCondition;
        return (T) this;
    }

    public T setLayBetCondition(Predicate<StatefulFootballTradeContext> layBetCondition) {
        this.layBetCondition = layBetCondition;
        return (T) this;
    }

    public T setEventCondition(Predicate<StatefulFootballTradeContext> eventCondition) {
        this.eventCondition = eventCondition;
        return (T) this;
    }

    public TotalPointsBettingRule createBackBettingRule() {
        return new TotalPointsBettingRule(description, order, points, totalType, backBetStartTime, backBetEndTime, backStake, maxLayPrice, layBetStartTime, layBetEndTime, minBackPrice, backBetAllowedDiff, layBetAllowedDiff, eventCondition, backBetCondition, layBetCondition);
    }
}