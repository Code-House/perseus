package org.code_house.perseus.astyanax.model;

import com.netflix.astyanax.Cluster;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.Serializer;
import com.netflix.astyanax.model.ColumnFamily;
import org.code_house.perseus.astyanax.ThriftColumn;

/**
 * Defines new column family with all types which are involved.
 *
 * This class allows to obtain a model instance which is used for querying cassandra
 * and column family definition used for DDL.
 *
 * @author dl02
 *
 * @param <V> Value type.
 * @param <R> Row key type.
 * @param <C> Column key type.
 */
public class ColumnFamilyDefinition<R, C, V> {

    /**
     * Row key type.
     */
    private final ThriftColumn<R> rowType;

    /**
     * Column key type.
     */
    private final ThriftColumn<C> columnType;

    /**
     * Value type.
     */
    private final ThriftColumn<V> valueType;

    /**
     * Name of column family.
     */
    private final String name;

    /**
     * Astyanax querying model.
     */
    private final ColumnFamily<R, C> model;

    public ColumnFamilyDefinition(String name, ThriftColumn<R> rowType, ThriftColumn<C> columnType, ThriftColumn<V> valueType) {
        this.name = name;
        this.rowType = rowType;
        this.columnType = columnType;
        this.valueType = valueType;
    
        this.model = new ColumnFamily<>(name, getRowSerializer(), getColumnSerializer(), getValueSerializer());
    }

    public ColumnFamily<R, C> getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    public ThriftColumn<V> getValueType() {
        return valueType;
    }

    public ThriftColumn<R> getRowType() {
        return rowType;
    }

    public ThriftColumn<C> getColumnType() {
        return columnType;
    }

    public Serializer<V> getValueSerializer() {
        return valueType.getSerializer();
    }

    public Serializer<R> getRowSerializer() {
        return rowType.getSerializer();
    }

    public Serializer<C> getColumnSerializer() {
        return columnType.getSerializer();
    }

    /**
     * Creates astyanax column family definition which later may be used for DDL
     * operations.
     * 
     * @param cluster Cassandra cluster.
     * @param keyspace Keyspace.
     * @return DDL definition.
     */
    public com.netflix.astyanax.ddl.ColumnFamilyDefinition getDefinition(Cluster cluster, Keyspace keyspace) {
        return cluster.makeColumnFamilyDefinition()
            .setKeyspace(keyspace.getKeyspaceName())
            .setName(name)
            .setDefaultValidationClass(valueType.getCassandraTypeName())
            .setKeyValidationClass(rowType.getCassandraTypeName())
            .setComparatorType(columnType.getCassandraTypeName());
    }

}
