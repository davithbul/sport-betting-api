package com.el.betting.sdk.v2.betoption.bettype.moneyline;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.BetOption;

import java.math.BigDecimal;


public interface MoneyLineBetOption extends MoneyLineBetOptionInfo, BetOption {

    BigDecimal getPrice();

    default BetType getBetType() {
        return BetType.MONEY_LINE;
    }
}
