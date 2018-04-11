package com.el.betting.sdk.v2.betoption.api;

import com.el.betting.sdk.v2.OddsFormat;

import java.math.BigDecimal;

/**
 * BetOption represents real betting option for the event.
 * It contains all the necessary data in order to make a bet.
 * Along with BetOptionInfo it also contains price in order to let the
 * client calculate stake and make a bet.
 */
public interface BetOption extends BetOptionInfo  {

    BigDecimal getPrice();

    OddsFormat getOddsFormat();

    void setPrice(BigDecimal price);

    void addProperty(String name, Object value);
}
