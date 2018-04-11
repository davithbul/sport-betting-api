package com.el.betting.sdk.v2.betoption.bettype.winner;

import com.el.betting.sdk.v2.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.Bet;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v3.account.BookmakerAccount;
import com.el.betting.sdk.v4.Participant;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public class RaceWinnerBet extends RaceWinnerBetOption implements RaceWinnerBetOptionInfo, Bet {
    private String id = UUID.randomUUID().toString();
    private BetStatus status = BetStatus.NEW;
    private Optional<String> betId = Optional.empty();
    private BookmakerAccount bookmakerAccount;
    private Stake stake;
    private LocalDateTime betTime;

    public RaceWinnerBet(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Participant participant, String distance, BigDecimal price, BettingPage bettingPage, Stake stake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, participant, distance, price, bettingPage, additionalProperties);
        this.stake = stake;
    }

    public RaceWinnerBet(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Participant participant, String distance, BigDecimal price, BettingPage bettingPage, Stake minStake, Stake maxStake, OddsFormat oddsFormat, Stake stake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, participant, distance, price, oddsFormat, bettingPage, minStake, maxStake, additionalProperties);
        this.stake = stake;
    }

    public RaceWinnerBet(AbstractBetOptionInfo betOptionInfo, Participant participant, String distance, BigDecimal price, Stake stake) {
        super(betOptionInfo, participant, distance, price);
        this.stake = stake;
    }

    public RaceWinnerBet(RaceWinnerBetOption raceWinnerBetOption, Stake stake) {
        super(raceWinnerBetOption);
        this.stake = stake;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Optional<String> getBetId() {
        return betId;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public BetStatus getStatus() {
        return status;
    }

    public void setStatus(BetStatus status) {
        this.status = status;
    }

    @Override
    public void setBetId(String betId) {
        this.betId = Optional.ofNullable(betId);
    }

    @Override
    public BookmakerAccount getBookmakerAccount() {
        return bookmakerAccount;
    }

    @Override
    public void setStake(Stake stake) {
        this.stake = stake;
    }

    @Override
    public Stake getStake() {
        return stake;
    }

    public void setBookmakerAccount(BookmakerAccount bookmakerAccount) {
        this.bookmakerAccount = bookmakerAccount;
    }

    @Override
    public LocalDateTime getBetTime() {
        return betTime;
    }

    public void setBetTime(LocalDateTime betTime) {
        this.betTime = betTime;
    }

    @Override
    public String toString() {
        return "RaceWinnerBet{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", betId=" + betId +
                ", bookmakerAccount=" + bookmakerAccount +
                ", stake=" + stake +
                ", betTime=" + betTime +
                "} " + super.toString();
    }
}
