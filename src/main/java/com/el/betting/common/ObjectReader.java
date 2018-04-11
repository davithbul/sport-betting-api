package com.el.betting.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

public class ObjectReader {

    public static Map<String, Object> readProperties(final Object thingy, Predicate<Object>... predicates) {
        final Map<String, Object> matchingProperties = new TreeMap<>();
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(thingy.getClass());
            for (final PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                try {
                    if(descriptor.getName().equalsIgnoreCase("class")) {
                        continue;
                    }
                    final Object propertyValue = descriptor.getReadMethod().invoke(thingy);
                    boolean allMatch = Arrays.stream(predicates).allMatch(objectPredicate -> objectPredicate.test(propertyValue));
                    if (allMatch) {
                        matchingProperties.put(descriptor.getName(), propertyValue);
                    }
                } catch (final IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        } catch (final IntrospectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return matchingProperties;
    }


    public static Object readFieldAsString(final Object thingy, String field, boolean useReadMethod) {
        final Map<String, Object> matchingProperties = new TreeMap<>();
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(thingy.getClass());
            for (final PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                if(!descriptor.getName().equalsIgnoreCase(field)) {
                    continue;
                }
                try {
                    if(useReadMethod) {
                        try {
                            return descriptor.getReadMethod().invoke(thingy);
                        } catch (Exception e) {
                            return descriptor.getValue(field);
                        }
                    }
                } catch (final IllegalArgumentException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        } catch (final IntrospectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return matchingProperties;
    }

    public static String readFieldAsString(Object object, String fieldName) {
        Object result;
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
                field.setAccessible(true);
            }
            result = field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return result == null ? null : String.valueOf(result);
    }
}
