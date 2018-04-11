package com.el.betting.sdk.v2.betoption.bettype.layback;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.Bet;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v3.account.BookmakerAccount;
import org.springframework.data.annotation.PersistenceConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


public class LayBackBet<T extends BetOption> extends LayBackBetOption implements Bet, Serializable {

    private String id = UUID.randomUUID().toString();
    private final T betOption;
    private BetStatus status = BetStatus.NEW;
    transient private Optional<String> betId = Optional.empty();
    private BookmakerAccount bookmakerAccount;
    private Stake stake;
    private LocalDateTime betTime;


    @PersistenceConstructor
    public LayBackBet(T betOption, BetExchangeType betExchangeType, BetPrice betPrice, Stake stake) {
        super(betOption, betExchangeType, betPrice);
        this.betOption = betOption;
        this.stake = stake;
    }

    public LayBackBet(T betOption, BetExchangeType betExchangeType, Stake stake) {
        super(betOption, betExchangeType, betOption.getPrice());
        this.betOption = betOption;
        this.stake = stake;
    }

    public T getBetOption() {
        return betOption;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public BetStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(BetStatus status) {
        this.status = status;
    }

    @Override
    public void setBetId(String betId) {
        this.betId = Optional.of(betId);
    }

    @Override
    public Optional<String> getBetId() {
        return betId;
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
    public LocalDateTime getBetTime() {
        return betTime;
    }

    @Override
    public void setBetTime(LocalDateTime betTime) {
        this.betTime = betTime;
    }

    //We implemented custom reader and writer because Optional is not serializable
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.betId.orElse(null));
    }

    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
        in.defaultReadObject();
        this.betId = Optional.ofNullable((String)in.readObject());
    }

    @Override
    public String toString() {
        return "LayBackBet{" +
                "id='" + id + '\'' +
                ", betOption=" + betOption +
                ", status=" + status +
                ", betId=" + betId +
                ", bookmakerAccount=" + bookmakerAccount +
                ", stake=" + stake +
                ", betTime=" + betTime +
                "} " + super.toString();
    }
}
