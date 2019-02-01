package org.connectorio.helenus.astyanax.model;

import com.google.common.primitives.Primitives;
import com.netflix.astyanax.serializers.*;
import org.apache.cassandra.db.marshal.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Default types supported by Cassandra and Astyanax.
 * 
 * @author dl02
 */
public final class DataType {

    /**
     * UUID type, representet by {@link java.util.UUID}.
     */
    public final static JavaType<java.util.UUID> UUID = new DefaultType<java.util.UUID>(java.util.UUID.class, UUIDType.class, UUIDSerializer.get());

    /**
     * String type, which is mapped to UTF8Type in cassandra.
     */
    public final static JavaType<String> STRING = new DefaultType<String>(String.class, UTF8Type.class, StringSerializer.get());

    /**
     * Long representation.
     */
    public final static JavaType<Long> LONG = new DefaultType<Long>(Long.class, LongType.class, LongSerializer.get());

    /**
     * Integer representation.
     */
    public final static JavaType<Integer> INTEGER = new DefaultType<Integer>(Integer.class, IntegerType.class, Int32Serializer.get());

    /**
     * Short representation.
     */
    public final static JavaType<Short> SHORT = new DefaultType<Short>(Short.class, IntegerType.class, ShortSerializer.get());

    /**
     * Byte representation.
     */
    public final static JavaType<Byte> BYTE = new DefaultType<Byte>(Byte.class, BytesType.class, ByteSerializer.get());

    /**
     * Float representation.
     */
    public final static JavaType<Float> FLOAT = new DefaultType<Float>(Float.class, FloatType.class, FloatSerializer.get());

    /**
     * Double representation.
     */
    public final static JavaType<Double> DOUBLE = new DefaultType<Double>(Double.class, DoubleType.class, DoubleSerializer.get());

    /**
     * Big integer representation.
     */
    public final static JavaType<BigInteger> BIG_INTEGER = new DefaultType<BigInteger>(BigInteger.class, IntegerType.class, BigIntegerSerializer.get());

    /**
     * Big decimal representation.
     */
    public final static JavaType<BigDecimal> BIG_DECIMAL = new DefaultType<BigDecimal>(BigDecimal.class, DecimalType.class, BigDecimalSerializer.get());

    /**
     * Boolean representation.
     */
    public final static JavaType<Boolean> BOOLEAN = new DefaultType<Boolean>(Boolean.class, BooleanType.class, BooleanSerializer.get());

    /**
     * Byte array representation.
     */
    public final static JavaType<byte[]> BYTE_ARRAY = new DefaultType<byte[]>(byte[].class, BytesType.class, BytesArraySerializer.get());

    /**
     * Byte buffer representation.
     */
    public final static JavaType<ByteBuffer> BYTE_BUFFER = new DefaultType<ByteBuffer>(ByteBuffer.class, BytesType.class, ByteBufferSerializer.get());

    /**
     * Date representation.
     */
    public final static JavaType<Date> DATE = new DefaultType<Date>(Date.class, DateType.class, DateSerializer.get());

    /**
     * Counter representation.
     */
    public final static JavaType<Long> COUNTER = new DefaultType<Long>(Long.class, CounterColumnType.class, LongSerializer.get());

    /**
     * 
     */
    public final static JavaType<Object> DEFAULT = new DefaultType<Object>(Object.class, BytesType.class, ObjectSerializer.get());

    /**
     * Static mapping between java classes and types used.
     */
    private final static Map<Class<?>, JavaType<?>> TYPE_MAP = new LinkedHashMap<Class<?>, JavaType<?>>();

    static {
        TYPE_MAP.put(java.util.UUID.class, UUID);
        TYPE_MAP.put(String.class, STRING);
        TYPE_MAP.put(Long.class, LONG);
        TYPE_MAP.put(Integer.class, INTEGER);
        TYPE_MAP.put(Short.class, SHORT);
        TYPE_MAP.put(Byte.class, BYTE);
        TYPE_MAP.put(Float.class, FLOAT);
        TYPE_MAP.put(Double.class, DOUBLE);
        TYPE_MAP.put(BigInteger.class, BIG_INTEGER);
        TYPE_MAP.put(BigDecimal.class, BIG_DECIMAL);
        TYPE_MAP.put(Boolean.class, BOOLEAN);
        TYPE_MAP.put(byte[].class, BYTE_ARRAY);
        TYPE_MAP.put(ByteBuffer.class, BYTE_BUFFER);
        TYPE_MAP.put(Date.class, DATE);
    }

    /**
     * Creates new composite type.
     * 
     * @param type Composite type class.
     * @return Composite type instance.
     */
    public static <T> Type<T> compositeType(Class<T> type) {
        return new CompositeType<T>(type, new AnnotatedCompositeSerializer<T>(type));
    }

    /**
     * Finds type for given Java type.
     * 
     * @param clazz Type.
     * @return Java type representation.
     */
    public static Type<?> of(Class<?> clazz) {
        if (clazz.isPrimitive())
        {
            return of(Primitives.wrap(clazz));
        }
        for (JavaType<?> type : TYPE_MAP.values()) {
            Class<?> javaType = type.getJavaType();
            if (javaType.isAssignableFrom(clazz)) {
                return type;
            }
        }
        return DEFAULT;
    }

}
