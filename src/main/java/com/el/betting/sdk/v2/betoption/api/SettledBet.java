package com.el.betting.sdk.v2.betoption.api;


import com.el.betting.sdk.common.SportType;
import com.el.betting.sdk.v2.BetExchangeType;
import com.el.betting.sdk.v2.BetOutcome;
import com.el.betting.sdk.v2.BetStatus;
import com.el.betting.sdk.v2.Sport;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * SettledBet represents the result / outcome of the event.
 * Could be enhanced in the future to represent also handicap,
 * exact score, who scored and more details.
 * Should be immutable
 */
@Document
public class SettledBet implements Cloneable {
    @Id
    private String betId;
    private long sportId;
    private long leagueId;
    private long eventId;
    private String lineId;
    private Integer selectionId;
    private BetStatus status;
    private EventDescription eventDescription;
    private BetExchangeType betExchangeType;
    private BetOutcome betOutcome;
    private LocalDateTime placedDate;
    private LocalDateTime settledDate;
    private LocalDateTime lastMatchedDate;
    private BigDecimal stake;
    private BigDecimal priceRequested;
    private BigDecimal price;
    private boolean priceReduced;
    private BigDecimal size;

    /**
     * Can be negative, if the bet is lost
     */
    private BigDecimal profit;
    private Map<String, Object> additionalProperties;

    public long getSportId() {
        return sportId;
    }

    public void setSportId(long sportId) {
        this.sportId = sportId;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public Integer getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Integer selectionId) {
        this.selectionId = selectionId;
    }

    public String getBetId() {
        return betId;
    }

    public void setBetId(String betId) {
        this.betId = betId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public BetStatus getStatus() {
        return status;
    }

    public void setStatus(BetStatus status) {
        this.status = status;
    }

    public BetExchangeType getBetExchangeType() {
        return betExchangeType;
    }

    public void setBetExchangeType(BetExchangeType betExchangeType) {
        this.betExchangeType = betExchangeType;
    }

    public BetOutcome getBetOutcome() {
        return betOutcome;
    }

    public void setBetOutcome(BetOutcome betOutcome) {
        this.betOutcome = betOutcome;
    }

    public LocalDateTime getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(LocalDateTime placedDate) {
        this.placedDate = placedDate;
    }

    public LocalDateTime getSettledDate() {
        return settledDate;
    }

    public void setSettledDate(LocalDateTime settledDate) {
        this.settledDate = settledDate;
    }

    public LocalDateTime getLastMatchedDate() {
        return lastMatchedDate;
    }

    public void setLastMatchedDate(LocalDateTime lastMatchedDate) {
        this.lastMatchedDate = lastMatchedDate;
    }

    public BigDecimal getStake() {
        return stake;
    }

    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    public BigDecimal getPriceRequested() {
        return priceRequested;
    }

    public void setPriceRequested(BigDecimal priceRequested) {
        this.priceRequested = priceRequested;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isPriceReduced() {
        return priceReduced;
    }

    public void setPriceReduced(boolean priceReduced) {
        this.priceReduced = priceReduced;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }


    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void addProperty(String name, Object value) {
        if (additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(name, value);
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public EventDescription getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(EventDescription eventDescription) {
        this.eventDescription = eventDescription;
    }

    public static class EventDescription {
        private String marketDesc;
        private String marketType;
        private LocalDateTime marketStartTime;
        private String runnerDesc;
        private Integer numberOfWinners;
        private Double eachWayDivisor;
        private SportType sportType;
        private String homeTeam;
        private String awayTeam;
        private String category;
        private String league;

        public void setSportType(SportType sportType) {
            this.sportType = sportType;
        }

        public SportType getSportType() {
            return sportType;
        }

        public void setHomeTeam(String homeTeam) {
            this.homeTeam = homeTeam;
        }

        public String getHomeTeam() {
            return homeTeam;
        }

        public void setAwayTeam(String awayTeam) {
            this.awayTeam = awayTeam;
        }

        public String getAwayTeam() {
            return awayTeam;
        }

        public void setMarketStartTime(LocalDateTime marketStartTime) {
            this.marketStartTime = marketStartTime;
        }

        public void setRunnerDesc(String runnerDesc) {
            this.runnerDesc = runnerDesc;
        }

        public String getRunnerDesc() {
            return runnerDesc;
        }

        public void setNumberOfWinners(Integer numberOfWinners) {
            this.numberOfWinners = numberOfWinners;
        }

        public Integer getNumberOfWinners() {
            return numberOfWinners;
        }

        public void setEachWayDivisor(Double eachWayDivisor) {
            this.eachWayDivisor = eachWayDivisor;
        }

        public Double getEachWayDivisor() {
            return eachWayDivisor;
        }

        public void setMarketDesc(String marketDesc) {
            this.marketDesc = marketDesc;
        }

        public String getMarketDesc() {
            return marketDesc;
        }

        public void setMarketType(String marketType) {
            this.marketType = marketType;
        }

        public String getMarketType() {
            return marketType;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCategory() {
            return category;
        }

        public void setLeague(String league) {
            this.league = league;
        }

        public String getLeague() {
            return league;
        }
    }

    public SettledBet clone() {
        SettledBet copySettledBet = new SettledBet();
        copySettledBet.betId = this.betId;
        copySettledBet.sportId = this.sportId;
        copySettledBet.leagueId = this.leagueId;
        copySettledBet.eventId = this.eventId;
        copySettledBet.lineId = this.lineId;
        copySettledBet.selectionId = this.selectionId;
        copySettledBet.status = this.status;
        copySettledBet.eventDescription = this.eventDescription;
        copySettledBet.betExchangeType = this.betExchangeType;
        copySettledBet.betOutcome = this.betOutcome;
        copySettledBet.placedDate = this.placedDate;
        copySettledBet.settledDate = this.settledDate;
        copySettledBet.lastMatchedDate = this.lastMatchedDate;
        copySettledBet.stake = this.stake;
        copySettledBet.priceRequested = this.priceRequested;
        copySettledBet.price = this.price;
        copySettledBet.priceReduced = this.priceReduced;
        copySettledBet.size = this.size;
        copySettledBet.profit = this.profit;
        copySettledBet.additionalProperties = this.additionalProperties!= null ? new HashMap<>(this.additionalProperties) : new HashMap<>();
        return copySettledBet;
    }
}
