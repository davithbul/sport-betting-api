package com.el.betting.sdk.v3.trading;

import com.el.betting.sdk.v2.BetType;
import com.el.betting.sdk.v2.TimeRange;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represents outcome of TradingRule after execution.
 * E.G. it might simulate any trading rule over any set of data, and then have data
 * representing as TradingRuleExecution.
 */
@Document
public class TradeRuleExecution {

    @Id
    private String id;

    /**
     * Represents start and end time of the run
     */
    @Indexed
    private TimeRange<LocalDateTime> runTime;

    /**
     * Represents the type of trading rule. We need this field
     * so we can do documents filtering beforehand.
     */
    @Indexed
    private BetType betType;

    @Transient
    private TradeRule tradeRule;

    @Indexed
    private String tradeRuleId;

    /**
     * Represents data set over which simulation rule was executed.
     */
    @Indexed
    private TimeRange<LocalDateTime> dataset;

    /**
     * Represents trade count over which tradingRule was simulated.
     */
    private int tradeCount;

    /**
     * Represents trade count which matched for the given trading rule.
     */
    private int matchedTradeCount;

    private double mean;

    private double profit;

    private Map<String, Object> additionalProperties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TimeRange<LocalDateTime> getRunTime() {
        return runTime;
    }

    public void setRunTime(TimeRange<LocalDateTime> runTime) {
        this.runTime = runTime;
    }

    public TradeRule getTradeRule() {
        return tradeRule;
    }

    public void setTradeRule(TradeRule tradeRule) {
        this.tradeRule = tradeRule;
    }

    public String getTradeRuleId() {
        return tradeRuleId;
    }

    public void setTradeRuleId(String tradeRuleId) {
        this.tradeRuleId = tradeRuleId;
    }

    public BetType getBetType() {
        return betType;
    }

    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    public TimeRange<LocalDateTime> getDataset() {
        return dataset;
    }

    public void setDataset(TimeRange<LocalDateTime> dataset) {
        this.dataset = dataset;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }

    public int getMatchedTradeCount() {
        return matchedTradeCount;
    }

    public void setMatchedTradeCount(int matchedTradeCount) {
        this.matchedTradeCount = matchedTradeCount;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public synchronized void addProperty(String name, Object value) {
        if(additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(name, value);
    }

    public Optional<Object> getProperty(String name) {
        return additionalProperties != null && additionalProperties.containsKey(name) ?
                Optional.ofNullable(additionalProperties.get(name)) :
                Optional.empty();
    }
}
