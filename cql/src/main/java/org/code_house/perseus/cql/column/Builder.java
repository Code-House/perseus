/*
 * (C) Copyright 2016 Code-House and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.code_house.perseus.cql.column;

import com.datastax.driver.core.DataType;

import java.util.Map;
import java.util.UUID;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class Builder<J, DT extends DataType, C extends CassandraColumn<J, DT>> {

    private final C column;

    protected Builder(C column) {
        this.column = column;
    }

    public C build() {
        return column;
    }

    public Builder<J, DT, NamedCassandraColumn<J, DT>> named(String name) {
        return new Builder<>(new NamedCassandraColumn(name, this.column));
    }

    public Builder<J, DT, PartitionKeyColumn<J, DT>> partitionKey() {
        return new Builder<>(new PartitionKeyColumn<>(this.column));
    }

    public Builder<J, DT, ClusteringKeyColumn<J, DT>> clusteringKey() {
        return new Builder<>(new ClusteringKeyColumn<>(this.column));
    }

    public static Builder<String, DataType.NativeType, StringColumn> string() {
        return new Builder<>(new StringColumn());
    }

    public static Builder<UUID, DataType.NativeType, UUIDColumn> uuid() {
        return new Builder<>(new UUIDColumn());
    }

    public static <K, V> Builder<Map<K, V>, DataType.CollectionType, MapColumn<K, V>> map(CassandraColumn<K, ?> key, CassandraColumn<V, ?> value) {
        return new Builder<>(new MapColumn<>(key, value));
    }

    public static StringColumn stringColumn() {
        return new StringColumn();
    }

    public static UUIDColumn uuidColumn() {
        return new UUIDColumn();
    }

}
