package com.el.betting.sdk.v3.trading;

import com.el.betting.sdk.v2.BetType;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.function.Predicate;

import static com.el.betting.common.Predicates.alwaysTrue;

/**
 * {@link BackLayBettingRule} is a trade rule type, which describes rule for back lay trading.
 * The pojo is immutable as it moves from one thread to other threads.
 */
@Immutable
public abstract class BackLayBettingRule extends TradeRule {
    private final String description;
    private final Order order;
    private final BigDecimal minBackPrice;
    private final BigDecimal backBetAllowedDiff;
    //for lay then back scenario, back stake is a stake which backer puts (i.e. lay user stake)
    private final BigDecimal backStake;
    private final int backBetStartTime;
    private final int backBetEndTime;

    private final BigDecimal maxLayPrice;
    private final BigDecimal layBetAllowedDiff;
    private final int layBetStartTime;
    private final int layBetEndTime;

    @Transient
    private final Predicate<StatefulFootballTradeContext> eventCondition;
    @Transient
    private final Predicate<StatefulFootballTradeContext> backBetCondition;
    @Transient
    private final Predicate<StatefulFootballTradeContext> layBetCondition;

    @PersistenceConstructor
    public BackLayBettingRule(BetType betType, String description, Order order, int backBetStartTime, int backBetEndTime, BigDecimal backStake, BigDecimal maxLayPrice, int layBetStartTime, int layBetEndTime, BigDecimal minBackPrice, BigDecimal backBetAllowedDiff, BigDecimal layBetAllowedDiff) {
        this(betType, description, order, backBetStartTime, backBetEndTime, backStake, maxLayPrice, layBetStartTime, layBetEndTime, minBackPrice, backBetAllowedDiff, layBetAllowedDiff, alwaysTrue(), alwaysTrue(), alwaysTrue());
    }

    public BackLayBettingRule(BetType betType, String description, Order order, int backBetStartTime, int backBetEndTime, BigDecimal backStake, BigDecimal maxLayPrice, int layBetStartTime, int layBetEndTime, BigDecimal minBackPrice, BigDecimal backBetAllowedDiff, BigDecimal layBetAllowedDiff, Predicate<StatefulFootballTradeContext> eventCondition, Predicate<StatefulFootballTradeContext> backBetCondition, Predicate<StatefulFootballTradeContext> layBetCondition) {
        super(betType);
        this.description = description;
        this.order = order;
        this.backBetStartTime = backBetStartTime;
        this.backBetEndTime = backBetEndTime;
        this.backStake = backStake;
        this.maxLayPrice = maxLayPrice;
        this.layBetStartTime = layBetStartTime;
        this.layBetEndTime = layBetEndTime;
        this.minBackPrice = minBackPrice;
        this.backBetAllowedDiff = backBetAllowedDiff;
        this.layBetAllowedDiff = layBetAllowedDiff;
        this.eventCondition = eventCondition;
        this.backBetCondition = backBetCondition;
        this.layBetCondition = layBetCondition;
    }

    public String getDescription() {
        return description;
    }

    public Order getOrder() {
        return order;
    }

    public int getBackBetStartTime() {
        return backBetStartTime;
    }

    public int getBackBetEndTime() {
        return backBetEndTime;
    }

    public BigDecimal getBackStake() {
        return backStake;
    }

    public BigDecimal getMaxLayPrice() {
        return maxLayPrice;
    }

    public int getLayBetStartTime() {
        return layBetStartTime;
    }

    public int getLayBetEndTime() {
        return layBetEndTime;
    }

    public BigDecimal getMinBackPrice() {
        return minBackPrice;
    }

    public BigDecimal getBackBetAllowedDiff() {
        return backBetAllowedDiff;
    }

    public BigDecimal getLayBetAllowedDiff() {
        return layBetAllowedDiff;
    }

    public Predicate<StatefulFootballTradeContext> getEventCondition() {
        return eventCondition;
    }

    public Predicate<StatefulFootballTradeContext> getBackBetCondition() {
        return backBetCondition;
    }

    public Predicate<StatefulFootballTradeContext> getLayBetCondition() {
        return layBetCondition;
    }

    @Override
    public String toString() {
        return "BackLayBettingRule {" +
                "order=" + order +
                ", backBetStartTime=" + backBetStartTime +
                ", backBetEndTime=" + backBetEndTime +
                ", backStake=" + backStake +
                ", maxLayPrice=" + maxLayPrice +
                ", layBetStartTime=" + layBetStartTime +
                ", layBetEndTime=" + layBetEndTime +
                ", minBackPrice=" + minBackPrice +
                ", allowedDifference=" + backBetAllowedDiff +
                ", layBetAllowedDiff=" + layBetAllowedDiff +
                (description.isEmpty() ? "" : ", " + description);
    }

    public enum Order implements Serializable {
        BACK_THEN_LAY,
        LAY_THEN_BACK,
        ANY
    }
}
