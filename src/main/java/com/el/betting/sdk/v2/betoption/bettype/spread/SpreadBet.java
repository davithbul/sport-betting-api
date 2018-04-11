package com.el.betting.sdk.v2.betoption.bettype.spread;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.Bet;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v3.account.BookmakerAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public class SpreadBet extends SpreadBetOption implements SpreadBetOptionInfo, Bet {
    private String id = UUID.randomUUID().toString();
    private BetStatus status = BetStatus.NEW;
    private Optional<String> betId = Optional.empty();
    private BookmakerAccount bookmakerAccount;
    private Stake stake;
    private LocalDateTime betTime;

    public SpreadBet(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, BigDecimal spread, BigDecimal price, BettingPage bettingPage, Stake stake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, team, spread, price, bettingPage, additionalProperties);
        this.stake = stake;
    }

    public SpreadBet(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, BigDecimal spread, BigDecimal price, BettingPage bettingPage, Stake stake, Stake minStake, Stake maxStake, OddsFormat oddsFormat, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, team, spread, price, oddsFormat, bettingPage, minStake, maxStake, additionalProperties);
        this.stake = stake;
    }

    public SpreadBet(AbstractBetOptionInfo betOptionInfo, Team team, BigDecimal spread, BigDecimal price, Stake stake) {
        super(betOptionInfo, team, spread, price);
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
}
