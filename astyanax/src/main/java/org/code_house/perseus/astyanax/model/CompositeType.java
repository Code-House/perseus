package org.code_house.perseus.astyanax.model;

import com.netflix.astyanax.Serializer;
import com.netflix.astyanax.annotations.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Specialization of {@link Type} interface designed for representing composite types.
 * 
 * @author dl02
 *
 * @param <T> Java type used for reading and storing values.
 */
public class CompositeType<T> implements Type<T> {

    /**
     * Astyanax serializer.
     */
    private final Serializer<T> serializer;

    /**
     * Components used in composite type.
     */
    private List<Class<?>> types;

    public CompositeType(Class<T> type, Serializer<T> serializer) {
        this.serializer = serializer;
        this.types = getFields(type, true);
    }

    @Override
    public Serializer<T> getSerializer() {
        return serializer;
    }

    @Override
    public String getCassandraName() {
        StringBuilder builder = new StringBuilder("CompositeType(");
        for (Iterator<Class<?>> iterator = types.iterator(); iterator.hasNext();) {
            builder.append(DataType.of(iterator.next()).getCassandraName());
            if (iterator.hasNext()) {
                builder.append(",");
            }
        }
        return builder.append(")").toString();
    }

    /**
     * Read all fields which are annotated with {@link Component} annotation.
     * 
     * @param type Type to read from.
     * @param recursuvely If search should go over parent classes.
     * @return Types of fields with {@link Component} annotation.
     */
    private List<Class<?>> getFields(Class<?> type, boolean recursuvely) {
        List<Class<?>> allFields = new ArrayList<Class<?>>();
        if (type.getDeclaredFields() != null && type.getDeclaredFields().length > 0) {
            for (Field field : type.getDeclaredFields()) {
                if (field.isAnnotationPresent(Component.class)) {
                    allFields.add(field.getType());
                }
            }
            if (recursuvely && type.getSuperclass() != null) {
                allFields.addAll(getFields(type.getSuperclass(), true));
            }
        }
        return allFields;
    }

}
