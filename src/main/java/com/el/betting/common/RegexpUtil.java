package com.el.betting.common;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpUtil {

    private static final Logger log = LoggerFactory.getLogger(RegexpUtil.class);

    public static String replaceVariables(String text, Object... values) {
        log.debug(String.format("Compiling url %s with values %s.", text, Arrays.toString(values)));

        StringBuffer result = new StringBuffer();
        Iterator<Object> valuesIterator = Arrays.asList(values).iterator();

        Matcher m = Pattern.compile("\\{(.*?)\\}").matcher(text);

        while (m.find()) {

            // What to replace
            String toReplace = m.group(1);

            String toInsert = null;

            // New value to insert
            if (valuesIterator.hasNext()) {
                toInsert = String.valueOf(valuesIterator.next());
                log.debug(String.format("Variable '%s' will be replaced with '%s'", toReplace, toInsert));
            }

            if (toInsert == null) {
                Optional<String> defaultValue = getDefaultValue(toReplace);
                if (defaultValue.isPresent()) {
                    toInsert = defaultValue.get();
                    log.debug(String.format("Variable '%s' will be replaced with default value '%s'", toReplace, toInsert));
                } else {
                    throw new RuntimeException("Can't resolve placeholder '" + toReplace + "'.");
                }
            }

            // Append replaced match.
            m.appendReplacement(result, toInsert);
        }
        m.appendTail(result);
        return result.toString();
    }

    public static String replaceObjectVariables(String text, Map<String, Object> values) {
        Map<String, String> stringValues = new LinkedHashMap<>();
        values.forEach((key, value) -> stringValues.put(key, String.valueOf(value)));
        return replaceVariables(text, stringValues);
    }

    public static String replaceVariables(String text, Map<String, String> values) {
        log.debug(String.format("Compiling url %s with values %s.", text, values));
        StringBuffer result = new StringBuffer();
        Matcher m = Pattern.compile("\\{(.*?)\\}").matcher(text);

        while (m.find()) {

            // What to replace
            String toReplace = m.group(1);

            // New value to insert
            String toInsert = values.get(getValueName(toReplace));

            if (toInsert == null) {
                Optional<String> defaultValue = getDefaultValue(toReplace);
                if (defaultValue.isPresent()) {
                    toInsert = defaultValue.get();
                    log.debug(String.format("Variable '%s' will be replaced with value '%s'", toReplace, toInsert));
                } else {
                    throw new RuntimeException("Can't resolve placeholder '" + toReplace + "'.");
                }
            } else {
                log.debug(String.format("Variable '%s' will be replaced with default value '%s'", toReplace, toInsert));
            }

            // Append replaced match.
            m.appendReplacement(result, toInsert);
        }
        m.appendTail(result);
        return result.toString();
    }

    public static String getValueName(String expression) {
        if (expression.contains(":")) {
            return expression.substring(0, expression.indexOf(":"));
        }
        return expression;
    }

    public static Optional<String> getDefaultValue(String expression) {
        if (expression.contains(":")) {
            return Optional.of(expression.substring(expression.indexOf(":") + 1));
        }
        return Optional.empty();
    }

    public static Optional<String> getRegexpGroup(String text, String regexp, int group) {
        Matcher matcher = Pattern.compile(regexp).matcher(" " + text + " ");

        if (matcher.find()) {
            String groupValue = matcher.group(group);
            return Optional.of(groupValue);
        } else {
            return Optional.empty();
        }
    }

    public static Map<Integer, String> getRegexpGroups(String text, String regexp) {
        Matcher matcher = Pattern.compile(regexp).matcher(" " + text + " ");

        Map<Integer, String> groups = new HashMap<>();
        if (matcher.find()) {
            for (int groupNumber = 1; groupNumber <= matcher.groupCount(); groupNumber++) {
                String groupValue = matcher.group(groupNumber);
                groups.put(groupNumber, groupValue);
            }

            return groups;
        } else {
            return groups;
        }
    }

    public static HashMap<String, Object> getUnMatchedValues(String text, Map<String, Object> values) {
        HashMap<String, Object> unmatchedValues = new HashMap<>(values);
        log.debug(String.format("Compiling url %s with values %s.", text, values));
        Matcher m = Pattern.compile("\\{(.*?)\\}").matcher(text);

        while (m.find()) {

            // What to replace
            String toReplace = m.group(1);

            // New value to insert
            if (unmatchedValues.containsKey(getValueName(toReplace))) {
                unmatchedValues.remove(getValueName(toReplace));
            }
        }
        return unmatchedValues;
    }
}
