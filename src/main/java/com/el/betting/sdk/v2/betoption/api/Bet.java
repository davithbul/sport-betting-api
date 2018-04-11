package com.el.betting.sdk.v2.betoption.api;

import com.el.betting.sdk.v2.BetStatus;
import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v3.account.BookmakerAccount;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Bet is a either done or ready to bet object data.
 * The status might be used to know if the bet already is done, or not.
 * The bet might also be failed. Along with BetOption it contains stake and
 * betID (if bet is done), and the status of bet.
 */
@Document
public interface Bet extends BetOption {

    double ALLOWED_DIFFERENCE = 0.1111;

    /**
     * Represents unique id of the bet in the system,
     * might or might not match with BetId.
     */
    @Id
    String getId();

    /**
     * Represents betId in the betting system.
     * e.g. in bookmaker Optional, because the service provider might not have betId.
     */
    Optional<String> getBetId();

    /**
     * The account of bookmaker, using which the bet has been done.
     */
    BookmakerAccount getBookmakerAccount();

    /**
     * The stake of the bet.
     */
    Stake getStake();

    /**
     * The status of bet.
     */
    BetStatus getStatus();

    /**
     * The time when bet was done.
     */
    LocalDateTime getBetTime();

    void setStake(Stake stake);
    void setStatus(BetStatus status);
    void setBetId(String betId);
    void setBetTime(LocalDateTime betTime);
}
