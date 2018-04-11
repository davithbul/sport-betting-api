package com.el.betting.sdk.v2.betoption.api;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v3.account.BookmakerAccount;
import com.el.betting.sdk.v4.Participant;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractBet extends AbstractBetOption implements Bet {
    private String id = UUID.randomUUID().toString();
    private Optional<String> betID = Optional.empty();
    private BetStatus betStatus = BetStatus.NEW;
    private BookmakerAccount bookmakerAccount;
    private Stake stake;

    public AbstractBet(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, BigDecimal price, BettingPage bettingPage, BookmakerAccount bookmakerAccount, Stake stake, Map<String, Object> additionalData) {
        super(event, eventID, selectionID, lineID, period, price, bettingPage, additionalData);
        this.bookmakerAccount = bookmakerAccount;
        this.stake = stake;
    }

    public AbstractBet(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, BigDecimal price, BookmakerAccount bookmakerAccount, Stake stake, BettingPage bettingPage, Stake minStake, Stake maxStake, OddsFormat oddsFormat, Map<String, Object> additionalData) {
        super(event, eventID, selectionID, lineID, period, price, oddsFormat, bettingPage, minStake, maxStake, additionalData);
        this.bookmakerAccount = bookmakerAccount;
        this.stake = stake;
    }

    @Override
    public BookmakerAccount getBookmakerAccount() {
        return bookmakerAccount;
    }

    @Override
    public Stake getStake() {
        return stake;
    }

    public void setStake(Stake stake) {
        this.stake = stake;
    }

    @Override
    public Optional<String> getBetId() {
        return betID;
    }

    public void setBetID(Optional<String> betID) {
        this.betID = betID;
    }

    @Override
    public String getId() {
        return id;
    }

    public BetStatus getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(BetStatus betStatus) {
        this.betStatus = betStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBet)) return false;

        AbstractBet that = (AbstractBet) o;

        if (betID != null ? !betID.equals(that.betID) : that.betID != null) return false;
        if (betStatus != that.betStatus) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(stake != null ? !stake.equals(that.stake) : that.stake != null);

    }

    @Override
    public int hashCode() {
        int result = betID != null ? betID.hashCode() : 0;
        result = 31 * result + (betStatus != null ? betStatus.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (stake != null ? stake.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractBet{" +
                "betID='" + betID + '\'' +
                ", betStatus=" + betStatus +
                ", id=" + id +
                ", stake=" + stake +
                "} " + super.toString();
    }
}
