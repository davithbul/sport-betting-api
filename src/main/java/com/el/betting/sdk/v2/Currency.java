package com.el.betting.sdk.v2;

import com.el.betting.sdk.v2.criteria.FeedCriterion;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Immutable
public class Currency implements FeedCriterion, Serializable {

    private final String code;
    private final String name;

    private static Map<String, Currency> currencyMap = new HashMap<>();

    private Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Currency of(String code) {
        if (currencyMap.containsKey(code)) {
            return currencyMap.get(code);
        }
        Currency currency = new Currency(code, code);
        currencyMap.putIfAbsent(code, currency);
        return currency;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (code != null ? !code.equals(currency.code) : currency.code != null) return false;
        return name != null ? name.equals(currency.name) : currency.name == null;

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
