package com.el.betting.sdk.v2.common;

import com.el.betting.sdk.v2.PropertyType;
import com.el.betting.sdk.v2.Team;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.stream.Collectors;

public class PropertyHelper {
    private Map<String, Object> additionalData;

    public PropertyHelper(Map<String, Object> additionalData) {
        this.additionalData = additionalData;
    }

    public Map<String, Object> getProperties(PropertyType... propertyTypes) {
        return additionalData.entrySet().stream()
                .map(entry -> {
                    for (PropertyType type : propertyTypes) {
                        if (entry.getKey().startsWith(type.getValue())) {
                            String param = entry.getKey().substring(type.getValue().length());
                            return Maps.immutableEntry(param, entry.getValue());
                        }
                    }
                    return null;
                }).filter(entry -> entry != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Optional<Object> getProperty(PropertyType propertyType, String propertyName) {
        String fullPropertyName = propertyName(propertyType, propertyName);
        Object value = additionalData.get(fullPropertyName);
        return value != null ? Optional.of(value) : Optional.empty();
    }

    public Optional<Object> getProperty(String propertyName, PropertyType... propertyTypes) {
        Optional<PropertyType> propertyTypeOptional = Arrays.stream(propertyTypes).filter(propertyType -> additionalData.containsKey(propertyName(propertyType, propertyName))).findFirst();
        if(propertyTypeOptional.isPresent()) {
            return Optional.of(additionalData.get(propertyName(propertyTypeOptional.get(), propertyName)));
        } else {
            return Optional.empty();
        }
    }

    public static String propertyName(PropertyType propertyType, String name) {
        return propertyType.getValue() + name;
    }

    public Map<String, Object> getGlobalProperties() {
        return additionalData.entrySet().stream()
                .filter(entry -> !entry.getKey().startsWith(String.valueOf(Team.Side.HOME)) &&
                        !entry.getKey().startsWith(String.valueOf(Team.Side.DRAW)) &&
                        !entry.getKey().startsWith(String.valueOf(Team.Side.AWAY)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
