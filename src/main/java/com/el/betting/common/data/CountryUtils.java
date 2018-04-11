package com.el.betting.common.data;

import com.el.betting.common.CharacterUtil;

import java.util.*;

public final class CountryUtils {

    public static boolean isCountryName(String name) {
        return CountryHolder.isoCountries.contains(CharacterUtil.removeSpecialCharacters(name));
    }

    /**
     * We use this for lazy initalization, as country list is a huge and we use
     * it rarely, so it will be initialized only when isCountryName method gets called,
     * that would be the time when CountryHolder class will be loaded and it's static
     * block will be executed.
     */
    private static class CountryHolder {
        private final static Set<String> isoCountries = new HashSet<>();
        static {
            String[] locales = Locale.getISOCountries();

            for (String countryCode : locales) {
                Locale obj = new Locale("", countryCode);
                isoCountries.add(obj.getDisplayCountry());
            }

            isoCountries.add("England");
            isoCountries.add("Northern Ireland");
            isoCountries.add("USA");
        }
    }
}
