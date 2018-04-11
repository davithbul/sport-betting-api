package com.el.betting.sdk.v3.account.payment;

import com.el.betting.sdk.v2.Currency;
import com.el.betting.sdk.v3.account.WalletAccount;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.HashSet;
import java.util.Set;

@Immutable
public class SkrillAccount extends WalletAccount {
    private final String email;
    private final Set<String> secondaryEmails;
    private final String password;

    public SkrillAccount(String email, Currency currency, String password) {
        this(email, currency, password, new HashSet<>());
    }

    public SkrillAccount(String email, Currency currency, String password, Set<String> secondaryEmails) {
        super(PaymentSystem.SKRILL, email, currency);
        this.email = email;
        this.secondaryEmails = secondaryEmails;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getSecondaryEmails() {
        return secondaryEmails;
    }

    public boolean hasEmail(@Nonnull String email) {
        return secondaryEmails.contains(email);
    }
}
