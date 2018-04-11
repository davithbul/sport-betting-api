package com.el.betting.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;

public class ObjectWriter {

    public static void writeObjectFields(Object thingy, Map<String, Object> updateFields) {
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(thingy.getClass());
            for (final PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                try {
                    String name = descriptor.getName();
                    if(updateFields.containsKey(name)) {
//                        descriptor.getWriteMethod().invoke(updateFields.get(name));
                        descriptor.setValue(name, updateFields.get(name));
                    }
                } catch (final IllegalArgumentException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        } catch (final IntrospectionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
