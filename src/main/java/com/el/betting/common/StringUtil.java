package com.el.betting.common;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isNotBlank(String... strings) {
        for (String string : strings) {
            if (StringUtils.isBlank(string)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllBlank(String... strings) {
        for (String string : strings) {
            if (!StringUtils.isBlank(string)) {
                return false;
            }
        }
        return true;
    }

    public static String firstNotBlank(String... strings) {
        for (String string : strings) {
            if(string != null) {
                return string;
            }
        }
        return null;
    }

    @Deprecated
    public static boolean containsPartiallyOld(String string1, String string2) {
        if (isAllBlank(string1, string2) || StringUtils.equalsIgnoreCase(string1, string2)) {
            return true;
        }

        if (!isNotBlank(string1, string2)) {
            return false;
        }

        //remove dots and make uppercase
        String string1Upper = StringUtils.replaceChars(string1, "-.(),", " ").toUpperCase();
        String string2Upper = StringUtils.replaceChars(string2, "-.(),", " ").toUpperCase();

        if (string1Upper.contains(string2Upper) || string2Upper.contains(string1Upper)) {
            return true;
        }

        //split the strings
        String[] string1Parts = StringUtils.split(string1Upper, ' ');
        String[] string2Parts = StringUtils.split(string2Upper, ' ');

        if (string1Parts.length == 1 && string2Parts.length == 1) {
            return false;
        }

        //parts should be equal
//        if (string1Parts.length != string2Parts.length) {
//            return false;
//        }

        //get shortest part index
        int minLength = Math.min(string1Parts.length, string2Parts.length);

        //now compare parts
        for (int partIndex = 0; partIndex < minLength; partIndex++) {
            if (!(StringUtil.containsPartiallyOld(string1Parts[partIndex], string2Parts[partIndex]) ||
                    (string1Upper.contains(string2Parts[partIndex]) && string2Upper.contains(string1Parts[partIndex])))) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsPartially(String string1, String string2, SimilarityUtils.Compliance compliance) {
        if (isAllBlank(string1, string2) || StringUtils.equalsIgnoreCase(string1, string2)) {
            return true;
        }

        if (!isNotBlank(string1, string2)) {
            return false;
        }

        String[] string1Parts = StringUtils.split(string1, ' ');
        String[] string2Parts = StringUtils.split(string2, ' ');

        switch (compliance) {
            case STRICT:
                if (string1Parts.length != string2Parts.length) {
                    return false;
                }
            case NORMAL:
                if (string1Parts.length != string2Parts.length &&
                        (Math.max(string1.length(), string2.length()) < 6)) {
                    return false;
                }
                //words length should be at least 55 percent
                if (Math.min(string1.length(), string2.length()) / Math.max(string1.length(), string2.length()) < 0.55) {
                    return false;
                }

            case LOOSE:
                //very short words, e.g. fC c
                if (string1.length() <= string1Parts.length * 2
                        || string2.length() <= string2Parts.length * 2) {
                    return false;
                }
                //words length should be at least 45%
                if (Math.min(string1.length(), string2.length()) / Math.max(string1.length(), string2.length()) < 0.45) {
                    return false;
                }
        }

        if (string1.contains(string2) || string2.contains(string1)) {
            return true;
        }

        String string1Regexp = string1.replaceAll(" ", ".*") + ".*";
        String string2Regexp = string2.replaceAll(" ", ".*") + ".*";

        return string1.matches(string2Regexp) || string2.matches(string1Regexp);
    }

    public static boolean isMatchAnyIgnoreCase(String source, String values) {
        return isMatchAnyIgnoreCase(source, StringUtils.split(values, "|"));
    }

    public static boolean isMatchAnyIgnoreCase(String source, String... values) {
        for (String value : values) {
            if (source.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    public static String removeBrackets(String word) {
        if (word.contains("(") && word.contains(")")
                && (word.indexOf("(") < word.indexOf(")"))) {
            String result = word.substring(0, word.indexOf("(")) + word.substring(word.indexOf(")") + 1);
            return removeBrackets(CharacterUtil.clean(result.trim()));
        } else {
            return word;
        }
    }

    public static String[] splitTwoStrings(String inputString, String delilmiter) {
        String[] splits = new String[2];
        Pattern pattern = Pattern.compile(delilmiter + "*");
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.find()) {
            splits[0] = inputString.substring(0, matcher.start()).trim();
            splits[1] = inputString.substring(matcher.end()).trim();
        }
        return splits;
    }

    public static String removeWords(String word, String... removeWords) {
        word = " " + word + " ";
        for (String s : removeWords) {
            word = word.replaceAll("(?i)\\W" + s + "\\W", " ");
        }
        return word.trim();
    }

    public static List<String> extractMatches(String word, String... expressions) {
        word = " " + word + " ";
        List<String> matches = new ArrayList<>();
        for (String expression : expressions) {
            if (word.matches(".*(?i)\\W" + expression + "\\W.*"))
                matches.add(expression);
        }
        return matches;
    }

    public static boolean anyMatch(String text, String[] words) {
        for(String word : words) {
            if(text.toUpperCase().contains(word.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static String capitalizeFirstLetters(String text) {
        String[] split = StringUtils.split(text, " ");
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : split) {
            stringBuilder.append(Character.toUpperCase(word.charAt(0)));
            if(word.length() > 1) {
                stringBuilder.append(word.substring(1).toLowerCase());
            }
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public static boolean containsAny(String word, String... words) {
        for (String s : words) {
            if(word.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAnyWord(String word, String... words) {
        for (String s : words) {
            if(containsWord(word, s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsWord(String word, String regexp) {
        return word.matches("(^|.*\\W)" + regexp +"(\\W.*|$)");
    }

    public static boolean matchesAnyWord(String word, String... regexp) {
        String allWords = StringUtils.join(regexp, '|');
        return word.matches(allWords);
    }
}
