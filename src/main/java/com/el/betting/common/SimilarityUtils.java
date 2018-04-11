package com.el.betting.common;

import org.apache.commons.lang3.StringUtils;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.Levenshtein;
import org.simmetrics.metrics.SmithWaterman;
import org.simmetrics.metrics.SmithWatermanGotoh;

import java.util.Arrays;

import static com.el.betting.common.CharacterUtil.removeWhiteSpaces;

public class SimilarityUtils {

    public static boolean totallyEqual(String string1, String string2) {
        if (StringUtils.equalsIgnoreCase(string1, string2)) {
            return true;
        }

        if (string1 == null || string2 == null) {
            return false;
        }

        string1 = CharacterUtil.deAccent(string1).toLowerCase();
        string2 = CharacterUtil.deAccent(string2).toLowerCase();

        return StringUtil.isAllBlank(string1, string2) || StringUtils.equalsIgnoreCase(string1, string2);
    }

    public static void validateSimilarity(String string1, String string2, double percent) {
        if(SimilarityUtils.getMatchLikelyhood(string1, string2) < percent) {
            throw new IllegalArgumentException(String.format("Expected and result teams are different: '%s' <> '%s'", string1, string2));
        }
    }

    public static boolean isSimilar(String string1, String string2) {
        return isSimilar(string1, string2, Compliance.NORMAL);
    }

    public static boolean isSimilar(String string1, String string2, Compliance compliance) {
        string1 = CharacterUtil.clean(CharacterUtil.deAccent(string1).toLowerCase());
        string2 = CharacterUtil.clean(CharacterUtil.deAccent(string2).toLowerCase());

        if (StringUtil.isAllBlank(string1, string2) || StringUtils.equalsIgnoreCase(string1, string2)) {
            return true;
        }

        if (!StringUtil.isNotBlank(string1, string2)) {
            return false;
        }

        return StringUtil.containsPartially(string1, string2, compliance)
                || new Levenshtein().distance(string1, string2) > compliance.percent
                || SimilarityUtils.isOverlap(string1, string2, compliance)
                || SimilarityUtils.equalParts(string1, string2, compliance)
                || ((compliance == Compliance.NORMAL || compliance == Compliance.LOOSE) && getMatchLikelyhood(string1, string2) >= 75)
                || ((compliance == Compliance.NORMAL || compliance == Compliance.LOOSE) && getMatchLikelyhood(removeWhiteSpaces(string1), removeWhiteSpaces(string2)) >= 75);
    }

    public static boolean isAbbreviation(String str1, String str2, Compliance compliance) {
        if (isFullAbbreviation(str1, str2, compliance)) {
            return true;
        }

        if (str1.charAt(0) != str2.charAt(0)) {
            return false;
        }

        switch (compliance) {
            case STRICT:
            case NORMAL:
                if (str1.charAt(str1.length() - 1) != str2.charAt(str2.length() - 1)) {
                    return false;
                }
            case LOOSE:
                if (Math.min(str1.length(), str2.length()) < 2 || str1.charAt(str1.length() - 1) != str2.charAt(str2.length() - 1) || str1.charAt(1) != str2.charAt(1)) {
                    return false;
                }
        }

        String abbreviation;
        String longName;

        if (str1.length() == str2.length()) {
            return false;
        } else if (str1.length() > str2.length()) {
            longName = str1;
            abbreviation = str2;
        } else {
            longName = str2;
            abbreviation = str1;
        }

        char[] longNameChars = new char[longName.length()];
        longName.getChars(0, longName.length(), longNameChars, 0);

        char[] abbreviationChars = new char[abbreviation.length()];
        abbreviation.getChars(0, abbreviation.length(), abbreviationChars, 0);

        int j = 0;
        for (int i = 0; i < longNameChars.length && j < abbreviationChars.length; i++) {
            if (longNameChars[i] == abbreviationChars[j]) {
                j++;
            }
        }

        return j == abbreviationChars.length;
    }

    /**
     * Checks full word abbreviation,
     * e.g. Paris sen germen == PSG
     */
    public static boolean isFullAbbreviation(String str1, String str2, Compliance compliance) {
        String abbreviation;
        String longName;

        if (str1.length() == str2.length() || Math.max(str1.length(), str2.length()) < 7 || Math.min(str1.length(), str2.length()) < 2) {
            return false;
        } else if (str1.length() > str2.length()) {
            longName = str1;
            abbreviation = str2;
        } else {
            longName = str2;
            abbreviation = str1;
        }

        String[] longNameParts = StringUtils.split(longName, ' ');
        if (longNameParts.length != abbreviation.length() || StringUtils.split(abbreviation, ' ').length != 1) {
            return false;
        }

        char[] abbreviationChars = new char[abbreviation.length()];
        abbreviation.getChars(0, abbreviation.length(), abbreviationChars, 0);

        char[] longNameAbbreviation = new char[longNameParts.length];
        for (int i = 0; i < longNameParts.length; i++) {
            longNameAbbreviation[i] = longNameParts[i].charAt(0);
        }

        switch (compliance) {
            case STRICT:
                return Arrays.equals(longNameAbbreviation, abbreviationChars);
            case NORMAL:
            case LOOSE:
                Arrays.sort(longNameAbbreviation);
                Arrays.sort(abbreviationChars);
                return Arrays.equals(longNameAbbreviation, abbreviationChars);
        }

        return false;
    }

    public static boolean isOverlap(String str1, String str2, Compliance compliance) {
        return containParts(str1, str2, compliance) || containParts(str2, str1, compliance);
    }

    public static boolean containParts(String string, String matchString, Compliance compliance) {
        String[] stringParts = StringUtils.split(string, ' ');
        String[] matchStringParts = StringUtils.split(matchString, ' ');

        switch (compliance) {
            case STRICT:
                if (stringParts.length != matchStringParts.length) {
                    return false;
                }
            case NORMAL:
                if (stringParts.length != matchStringParts.length && matchString.length() < 5) {
                    return false;
                }
            case LOOSE:
                if (matchString.length() <= matchStringParts.length * 2) {
                    return false;
                }
        }

        float matchingPercent = 0;

        for (String matchingPart : matchStringParts) {
            boolean isMatched = false;

            for (String stringPart : stringParts) {
                if (stringPart.matches(matchingPart + ".*")) {
                    matchingPercent += (float) Math.min(matchingPart.length(), stringPart.length()) / Math.max(matchingPart.length(), stringPart.length());
                    isMatched = true;
                    break;
                } else if (isAbbreviation(matchingPart, stringPart, compliance)) {
                    matchingPercent += (float) Math.min(matchingPart.length(), stringPart.length()) / Math.max(matchingPart.length(), stringPart.length());
                    isMatched = true;
                    break;
                }
            }

            if (!isMatched) {
                return false;
            }
        }

        matchingPercent += Math.min((float) matchString.length(), string.length()) / Math.max(matchString.length(), string.length()) / 2;
        matchingPercent += Math.abs(stringParts.length - matchStringParts.length) < 1 ? 0.6 : 0;
        matchingPercent += Math.abs(stringParts.length - matchStringParts.length) == 1 ? 0.25 : 0;
        matchingPercent += Math.min(stringParts.length, matchStringParts.length) > 2 ? 0.5 : 0;
        matchingPercent += Math.min(stringParts.length, matchStringParts.length) == 2 ? 0.1 : 0;

        return matchingPercent > compliance.percent + 0.22;
    }

    /**
     * Pretty safe comparison, when some parts are really matching
     * like Real madrid == R madrid
     */
    public static boolean equalParts(String str1, String str2, Compliance compliance) {
        String[] str1Parts = StringUtils.split(str1, ' ');
        String[] str2Parts = StringUtils.split(str2, ' ');

        switch (compliance) {
            case STRICT:
            case NORMAL:
                if (str1Parts.length != str2Parts.length && Math.min(str1.length(), str1.length()) < 4) {
                    return false;
                }
            case LOOSE:
                if (str1.length() <= str1Parts.length * 2 || str2.length() <= str2Parts.length * 2) {
                    return false;
                }
        }

        float matchingPercent = 0;
        int matchingLetterCount = 0;

        for (String str2Part : str2Parts) {
            for (String str1Part : str1Parts) {
                if (str1Part.equalsIgnoreCase(str2Part) || new Levenshtein().distance(str1Part, str2Part) > compliance.percent) {
                    matchingPercent += Math.min((float) str1Part.length() / str1.length(), (float) str2Part.length() / str2.length());
                    matchingLetterCount += str1Part.length();
                    break;
                }
            }
        }

        switch (compliance) {
            case STRICT:
                return matchingPercent > 0.70;
            case NORMAL:
                return matchingPercent > 0.56 || ((float) matchingLetterCount / Math.min(str1.length(), str2.length()) > 0.8);
            case LOOSE:
                return matchingPercent > 0.45 || matchingLetterCount >= 4;
        }

        return matchingPercent > compliance.percent;
    }

    public static float getMatchLikelyhood(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return (str1 == str2) ? 100 : 0;
        }

        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        StringMetric metric;
        float avg = 0F, result;
        metric = new SmithWaterman();
        result = metric.compare(str1, str2);
        avg += result;
        metric = new SmithWatermanGotoh();
        result = metric.compare(str1, str2);
        avg += result;
        return (avg / 4.0F) * 100.0F;
    }

    private static void outputResult(final float result, final StringMetric metric) {
        System.out.println("Using Metric " + metric + " gives a similarity score of " + (result * 100 / 1) + "%");
    }

    public enum Compliance {
        STRICT(0.95f),
        NORMAL(0.9f),
        LOOSE(0.85f);

        public final float percent;

        Compliance(float percent) {
            this.percent = percent;
        }
    }

}
