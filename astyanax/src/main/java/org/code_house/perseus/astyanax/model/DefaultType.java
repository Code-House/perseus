package org.code_house.perseus.astyanax.model;

import com.netflix.astyanax.Serializer;

/**
 * Default implementation of {@link JavaType} interface.
 * 
 * This class has non-public constructor so developers should not define new types,
 * instead of this they should add type definition to DataType class.
 *
 * @author dl02
 *
 * @param <T> Type of serializer and Java class.
 */
public class DefaultType<T> implements JavaType<T> {

    private final Class<T> type;
    private final Class<?> cassandraType;
    private final Serializer<T> serializer;

    DefaultType(Class<T> javaType, Class<?> cassandraType, Serializer<T> serializer) {
        this.type = javaType;
        this.cassandraType = cassandraType;
        this.serializer = serializer;
    }

    @Override
    public Serializer<T> getSerializer() {
        return serializer;
    }

    @Override
    public String getCassandraName() {
        return cassandraType.getSimpleName();
    }

    public Class<?> getCassandraType() {
        return cassandraType;
    }

    @Override
    public Class<T> getJavaType() {
        return type;
    }

}
