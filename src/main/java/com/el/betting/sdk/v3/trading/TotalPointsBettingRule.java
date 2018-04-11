package com.el.betting.sdk.v3.trading;

import com.el.betting.sdk.v2.BetType;
import com.el.betting.sdk.v2.TotalType;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.annotation.concurrent.Immutable;
import java.math.BigDecimal;
import java.util.function.Predicate;

import static com.el.betting.common.Predicates.alwaysTrue;

/**
 * {@link TotalPointsBettingRule} is a trade rule type, which describes rule for total points betting.
 * The pojo is immutable as it moves from one thread to other threads.
 */
@Immutable
public class TotalPointsBettingRule extends BackLayBettingRule {
    private double points;
    private final TotalType totalType;

    @PersistenceConstructor
    public TotalPointsBettingRule(String description, Order order, double points, TotalType totalType, int backBetStartTime, int backBetEndTime, BigDecimal backStake, BigDecimal maxLayPrice, int layBetStartTime, int layBetEndTime, BigDecimal minBackPrice, BigDecimal backBetAllowedDiff, BigDecimal layBetAllowedDiff) {
        this(description, order, points, totalType, backBetStartTime, backBetEndTime, backStake, maxLayPrice, layBetStartTime, layBetEndTime, minBackPrice, backBetAllowedDiff, layBetAllowedDiff, alwaysTrue(), alwaysTrue(), alwaysTrue());
    }

    public TotalPointsBettingRule(String description, Order order, double points, TotalType totalType, int backBetStartTime, int backBetEndTime, BigDecimal backStake, BigDecimal maxLayPrice, int layBetStartTime, int layBetEndTime, BigDecimal minBackPrice, BigDecimal backBetAllowedDiff, BigDecimal layBetAllowedDiff, Predicate<StatefulFootballTradeContext> eventCondition, Predicate<StatefulFootballTradeContext> backBetCondition, Predicate<StatefulFootballTradeContext> layBetCondition) {
        super(BetType.TOTAL_POINTS, description, order, backBetStartTime, backBetEndTime, backStake, maxLayPrice, layBetStartTime, layBetEndTime, minBackPrice, backBetAllowedDiff, layBetAllowedDiff, eventCondition, backBetCondition, layBetCondition);
        this.points = points;
        this.totalType = totalType;
    }

    public double getPoints() {
        return points;
    }

    public TotalType getTotalType() {
        return totalType;
    }

    @Override
    public String toString() {
        return "TotalPointsBettingRule{" +
                "points=" + points +
                ", totalType=" + totalType +
                "} " + super.toString();
    }
}
