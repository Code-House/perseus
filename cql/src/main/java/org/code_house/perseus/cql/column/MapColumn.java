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
import com.datastax.driver.core.GettableByIndexData;
import com.datastax.driver.core.GettableByNameData;
import com.datastax.driver.core.GettableData;

import java.util.Map;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class MapColumn<K, V> extends CollectionColumn<Map<K, V>> {

    private final CassandraColumn<K, ?> keyColumn;
    private final CassandraColumn<V, ?> valueColumn;

    public MapColumn(CassandraColumn<K, ?> keyColumn, CassandraColumn<V, ?> valueColumn) {
        super(DataType.map(keyColumn.getCassandraType(), valueColumn.getCassandraType()));
        this.keyColumn = keyColumn;
        this.valueColumn = valueColumn;
    }

    @Override
    public Map<K, V> fromData(String name, GettableByNameData data) {
        return data.getMap(name, keyColumn.getJavaType(), valueColumn.getJavaType());
    }

    @Override
    public Map<K, V> fromData(int index, GettableByIndexData data) {
        return data.getMap(index, keyColumn.getJavaType(), valueColumn.getJavaType());
    }

    @Override
    public Class<Map<K, V>> getJavaType() {
        return (Class<Map<K, V>>) (Object) Map.class;
    }

}
