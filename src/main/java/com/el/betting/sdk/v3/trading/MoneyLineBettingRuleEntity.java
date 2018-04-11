package com.el.betting.sdk.v3.trading;

import com.el.betting.sdk.v2.Team;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.function.Predicate;

/**
 * Represents dto object for {@link MoneyLineBettingRule}. Unlike BackLayBettingRule
 * it contains mutable id objects which assigns to the class after persisting.
 */
@Document
public class MoneyLineBettingRuleEntity extends MoneyLineBettingRule implements Entity<String> {

    @Id
    private String id;

    public MoneyLineBettingRuleEntity(String description, Order order, Team.Side side, int backBetStartTime, int backBetEndTime, BigDecimal backStake, int layBetStartTime, int layBetEndTime, BigDecimal minBackPrice, BigDecimal maxLayPrice, BigDecimal backBetAllowedDiff, BigDecimal layBetAllowedDiff, Predicate<StatefulFootballTradeContext> eventCondition, Predicate<StatefulFootballTradeContext> backBetCondition, Predicate<StatefulFootballTradeContext> layBetCondition) {
        super(description, order, side, backBetStartTime, backBetEndTime, backStake, layBetStartTime, layBetEndTime, minBackPrice, maxLayPrice, backBetAllowedDiff, layBetAllowedDiff, eventCondition, backBetCondition, layBetCondition);
    }

    public MoneyLineBettingRuleEntity() {
        super(null, null, null, 0, 0, null, 0, 0, null, null, null, null);
    }

    public MoneyLineBettingRuleEntity(MoneyLineBettingRule bettingRule) {
        super(bettingRule.getDescription(),
                bettingRule.getOrder(),
                bettingRule.getSide(),
                bettingRule.getBackBetStartTime(),
                bettingRule.getBackBetEndTime(),
                bettingRule.getBackStake(),
                bettingRule.getLayBetStartTime(),
                bettingRule.getLayBetEndTime(),
                bettingRule.getMinBackPrice(),
                bettingRule.getMaxLayPrice(),
                bettingRule.getBackBetAllowedDiff(),
                bettingRule.getLayBetAllowedDiff(),
                bettingRule.getEventCondition(),
                bettingRule.getBackBetCondition(),
                bettingRule.getLayBetCondition());
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
