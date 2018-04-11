package com.el.betting.sdk.v3.trading;

import com.el.betting.sdk.v2.TotalType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.function.Predicate;

/**
 * Represents dto object for backLayBettingRule. Unlike BackLayBettingRule
 * it contains mutable id objects which assigns to the class after
 * persisting.
 */
@Document
public class TotalPointsBettingRuleEntity extends TotalPointsBettingRule implements Entity<String> {

    @Id
    private String id;

    public TotalPointsBettingRuleEntity(String description, Order order, double points, TotalType totalType, int backBetStartTime, int backBetEndTime, BigDecimal backStake, BigDecimal maxLayPrice, int layBetStartTime, int layBetEndTime, BigDecimal minBackPrice, BigDecimal backBetAllowedDiff, BigDecimal layBetAllowedDiff, Predicate<StatefulFootballTradeContext> eventCondition, Predicate<StatefulFootballTradeContext> backBetCondition, Predicate<StatefulFootballTradeContext> layBetCondition) {
        super(description, order, points, totalType, backBetStartTime, backBetEndTime, backStake, maxLayPrice, layBetStartTime, layBetEndTime, minBackPrice, backBetAllowedDiff, layBetAllowedDiff, eventCondition, backBetCondition, layBetCondition);
    }

    public TotalPointsBettingRuleEntity() {
        super(null, null, 0, null, 0, 0, null, null, 0, 0, null, null, null, null, null, null);
    }

    public TotalPointsBettingRuleEntity(TotalPointsBettingRule totalPointsBettingRule) {
        super(totalPointsBettingRule.getDescription(),
                totalPointsBettingRule.getOrder(),
                totalPointsBettingRule.getPoints(),
                totalPointsBettingRule.getTotalType(),
                totalPointsBettingRule.getBackBetStartTime(),
                totalPointsBettingRule.getBackBetEndTime(),
                totalPointsBettingRule.getBackStake(),
                totalPointsBettingRule.getMaxLayPrice(),
                totalPointsBettingRule.getLayBetStartTime(),
                totalPointsBettingRule.getLayBetEndTime(),
                totalPointsBettingRule.getMinBackPrice(),
                totalPointsBettingRule.getBackBetAllowedDiff(),
                totalPointsBettingRule.getLayBetAllowedDiff(),
                totalPointsBettingRule.getEventCondition(),
                totalPointsBettingRule.getBackBetCondition(),
                totalPointsBettingRule.getLayBetCondition());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
