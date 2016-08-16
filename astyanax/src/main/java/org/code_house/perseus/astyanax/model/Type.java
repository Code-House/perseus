package org.code_house.perseus.astyanax.model;

import com.netflix.astyanax.Serializer;

/**
 * Representation of type used for defining column types.
 * 
 * @author dl02
 *
 * @param <T> Astyanax serialization type.
 */
public interface Type<T> {

    /**
     * Returns astyanax serializer responsible for reading/writing value.
     * 
     * @return Serializer.
     */
    Serializer<T> getSerializer();

    /**
     * Returns name of the cassandra type.
     * 
     * @return Cassandra type name.
     */
    String getCassandraName();

}
