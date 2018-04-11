package com.el.betting.sdk.v2.betoption.api;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.util.Map;

/**
 * {@link BetOptionInfo} is a metadata about bet option. It should help the client
 * to get details of betOption - e.g. price, maxStake,etc.
 * In a visual way we could imagine BetOptionInfo as a pointer with all details
 * pointing to the betOption.
 *
 * BetOption example is in Real vs Barca game the win of real, and meta
 * describes the selectionID, the place, bookmaker, etc information about the option.
 */
@Immutable
public interface BetOptionInfo extends Serializable {

    String getSelectionID();

    String getLineID();

    BetType getBetType();

    Event<? extends Participant> getEvent();

    long getEventID();

    Period getPeriod();

    Stake getMinStake();

    Stake getMaxStake();

    BettingPage getBettingPage();

    Map<String, Object> getAdditionalProperties();
}
