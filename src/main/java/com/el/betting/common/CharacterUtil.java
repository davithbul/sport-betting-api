package com.el.betting.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class CharacterUtil {

    public static String deAccent(String text) {
        if(text == null) {
            return null;
        }

        text = org.apache.commons.lang3.StringUtils.stripAccents(text);
        text = removeSpecialCharacters(text);
        text = updateMaliciousCharacters(text);
        String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static String clean(String text) {
        text = text.replaceAll("[^\\w\\s]", " ");
        return text.replaceAll(" +", " ").trim();
    }

    public static String updateMaliciousCharacters(String text) {
        return org.apache.commons.lang3.StringUtils.replaceChars(text, '/', '-');
    }

    public static String removeSpecialCharacters(String text) {
        text = org.apache.commons.lang3.StringUtils.replaceChars(text, ",[]{}!@#$%^&|;:?~-_+=*()", " ");
        return text.replaceAll(" +", " ").trim();
    }

    public static String decodeURL(String text) {
        text = text.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
        text = text.replaceAll("\\+", "%2B");
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String removeWhiteSpaces(String string) {
        return string.replaceAll("\\s+","");
    }
}
