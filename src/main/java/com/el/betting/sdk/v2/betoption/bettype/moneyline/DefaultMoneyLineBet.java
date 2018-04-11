package com.el.betting.sdk.v2.betoption.bettype.moneyline;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v3.account.BookmakerAccount;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public class DefaultMoneyLineBet extends DefaultMoneyLineBetOption implements MoneyLineBet {
    private String id = UUID.randomUUID().toString();
    private BetStatus status = BetStatus.NEW;
    private Optional<String> betId = Optional.empty();
    private BookmakerAccount bookmakerAccount;
    private Stake stake;
    private LocalDateTime betTime;

    @PersistenceConstructor
    public DefaultMoneyLineBet(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, BigDecimal price, BookmakerAccount bookmakerAccount, Stake stake, BettingPage bettingPage, Stake minStake, Stake maxStake, OddsFormat oddsFormat, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, team, price, oddsFormat, bettingPage, minStake, maxStake, additionalProperties);
        this.bookmakerAccount = bookmakerAccount;
        this.stake = stake;
    }

    public DefaultMoneyLineBet(MoneyLineBetOptionInfo betOptionInfo, BigDecimal price, Team team, BookmakerAccount bookmakerAccount, Stake stake) {
        super(betOptionInfo, price);
        this.bookmakerAccount = bookmakerAccount;
        this.stake = stake;
    }

    public DefaultMoneyLineBet(MoneyLineBetOption betOption, Stake stake) {
        super(betOption, betOption.getPrice());
        this.stake = stake;
    }

    @Override
    public BetType getBetType() {
        return BetType.MONEY_LINE;
    }

    @Override
    public BookmakerAccount getBookmakerAccount() {
        return bookmakerAccount;
    }

    public void setBookmakerAccount(BookmakerAccount bookmakerAccount) {
        this.bookmakerAccount = bookmakerAccount;
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
        return betId;
    }

    public void setBetId(String betId) {
        this.betId = Optional.of(betId);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public BetStatus getStatus() {
        return status;
    }

    public void setStatus(BetStatus status) {
        this.status = status;
    }

    @Override
    public LocalDateTime getBetTime() {
        return betTime;
    }

    public void setBetTime(LocalDateTime betTime) {
        this.betTime = betTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultMoneyLineBet)) return false;

        DefaultMoneyLineBet that = (DefaultMoneyLineBet) o;

        if (betId != null ? !betId.equals(that.betId) : that.betId != null) return false;
        if (status != that.status) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(stake != null ? !stake.equals(that.stake) : that.stake != null);

    }

    @Override
    public int hashCode() {
        int result = betId != null ? betId.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (stake != null ? stake.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MoneyLineBet{" +
                "betID='" + betId + '\'' +
                ", status=" + status +
                ", id=" + id +
                ", stake=" + stake +
                ", betTime=" + betTime +
                "} " + super.toString();
    }
}
