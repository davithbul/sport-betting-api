package com.el.betting.sdk.v3.account;

import com.el.betting.sdk.v2.Currency;
import com.el.betting.sdk.v2.provider.Bookmaker;
import com.el.betting.sdk.v3.account.payment.PaymentSystem;
import com.google.common.base.Preconditions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents general user of a system.
 */
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDay;
    private String country;
    private Currency defaultCurrency;
    private List<WalletAccount> walletAccounts;
    private List<BookmakerAccount> bookmakerAccounts;

    public User() {
        this.walletAccounts = new ArrayList<>();
        this.bookmakerAccounts = new ArrayList<>();
    }

    public User(String firstName, String lastName, String email, LocalDate birthDay, String country, Currency defaultCurrency, List<WalletAccount> walletAccounts, List<BookmakerAccount> bookmakerAccounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDay = birthDay;
        this.country = country;
        this.defaultCurrency = defaultCurrency;
        this.walletAccounts = walletAccounts;
        this.bookmakerAccounts = bookmakerAccounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public List<WalletAccount> getWalletAccounts() {
        return walletAccounts;
    }

    public WalletAccount getWalletAccount(PaymentSystem paymentSystem) {
        Optional<WalletAccount> walletAccountOptional = getWalletAccounts().stream().filter(walletAccount -> walletAccount.getPaymentSystem() == paymentSystem)
                .findAny();
        Preconditions.checkArgument(walletAccountOptional.isPresent(), "Can't find wallet account for user " + getFirstName() + ", wallet " + paymentSystem);
        return walletAccountOptional.get();
    }

    public void addWalletAccount(WalletAccount walletAccount) {
        this.walletAccounts.add(walletAccount);
    }

    public List<BookmakerAccount> getBookmakerAccounts() {
        return bookmakerAccounts;
    }

    public BookmakerAccount getBookmakerAccount(Bookmaker bookmaker) {
        return bookmakerAccounts.stream().filter(bookmakerAccount -> bookmakerAccount.getBookmaker() == bookmaker).findAny().get();
    }

    public void addBookmakerAccount(BookmakerAccount bookmakerAccount) {
        this.bookmakerAccounts.add(bookmakerAccount);
    }
}
