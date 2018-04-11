package com.el.betting.sdk.v2;

import java.math.BigDecimal;

/**
 * Balance is a plain object which represents balance in any system
 * i.e. Bookmaker Account, WalletAccount or for any other accounts.
 */
public class Balance {

    /**
     * availableBalance represents the amount which are ready for withdrawal or other usage immediately
     */
    private BigDecimal availableBalance;
    private Currency currency;
    /**
     * balance is a full balance, along with frozen amount, by default it's equal to available balance
     */
    private BigDecimal balance;
    private BigDecimal outstandingTransactions;
    private BigDecimal credit;

    public Balance(BigDecimal availableBalance, Currency currency) {
        this(availableBalance, currency, availableBalance, BigDecimal.ZERO, BigDecimal.ZERO);
    }


    public Balance(BigDecimal availableBalance, Currency currency, BigDecimal balance, BigDecimal outstandingTransactions, BigDecimal credit) {
        this.availableBalance = availableBalance;
        this.currency = currency;
        this.balance = balance;
        this.outstandingTransactions = outstandingTransactions;
        this.credit = credit;
    }

    public BigDecimal getOutstandingTransactions() {
        return outstandingTransactions;
    }

    public void setOutstandingTransactions(BigDecimal outstandingTransactions) {
        this.outstandingTransactions = outstandingTransactions;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void increaseAvailableBalance(BigDecimal amount) {
        availableBalance = availableBalance.add(amount);
    }

    public void reduceAvailableBalance(BigDecimal amount) {
        availableBalance = availableBalance.subtract(amount);
    }

    public void enhanceAvailableBalance(BigDecimal amount) {
        availableBalance = availableBalance.add(amount);
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "currency=" + currency +
                ", availableBalance=" + availableBalance +
                ", balance=" + balance +
                ", outstandingTransactions=" + outstandingTransactions +
                ", credit=" + credit +
                '}';
    }
}
