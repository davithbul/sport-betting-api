package com.el.betting.sdk.v3.trading;

import com.el.betting.sdk.v2.BetType;
import com.el.betting.sdk.v2.Team;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.annotation.concurrent.Immutable;
import java.math.BigDecimal;
import java.util.function.Predicate;

import static com.el.betting.common.Predicates.alwaysTrue;

/**
 * {@link MoneyLineBettingRule} is a trade rule type, which describes rule for backing and than laying
 * on a match result.
 * The pojo is immutable as it moves from one thread to other threads.
 */
@Immutable
public class MoneyLineBettingRule extends BackLayBettingRule {
    private final Team.Side side;

    @PersistenceConstructor
    public MoneyLineBettingRule(String description, Order order, Team.Side side, int backBetStartTime, int backBetEndTime, BigDecimal backStake, int layBetStartTime, int layBetEndTime, BigDecimal minBackPrice, BigDecimal maxLayPrice, BigDecimal backBetAllowedDiff, BigDecimal layBetAllowedDiff) {
        this(description, order, side, backBetStartTime, backBetEndTime, backStake, layBetStartTime, layBetEndTime, minBackPrice, maxLayPrice, backBetAllowedDiff, layBetAllowedDiff, alwaysTrue(), alwaysTrue(), alwaysTrue());
    }

    public MoneyLineBettingRule(String description, Order order, Team.Side side, int backBetStartTime, int backBetEndTime, BigDecimal backStake, int layBetStartTime, int layBetEndTime, BigDecimal minBackPrice, BigDecimal maxLayPrice, BigDecimal backBetAllowedDiff, BigDecimal layBetAllowedDiff, Predicate<StatefulFootballTradeContext> eventCondition, Predicate<StatefulFootballTradeContext> backBetCondition, Predicate<StatefulFootballTradeContext> layBetCondition) {
        super(BetType.MONEY_LINE, description, order, backBetStartTime, backBetEndTime, backStake, maxLayPrice, layBetStartTime, layBetEndTime, minBackPrice, backBetAllowedDiff, layBetAllowedDiff, eventCondition, backBetCondition, layBetCondition);
        this.side = side;
    }

    public Team.Side getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "MoneyLineBettingRule{" +
                "side=" + side +
                "} " + super.toString();
    }
}
