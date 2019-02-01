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
package org.connectorio.helenus.astyanax;

import com.netflix.astyanax.Cluster;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.ddl.ColumnFamilyDefinition;
import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.astyanax.model.Type;
import org.code_house.perseus.column.*;
import org.connectorio.helenus.api.column.NamedColumn;
import org.connectorio.helenus.api.column.DelegateColumn;

import java.util.*;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class ThriftDDL {

    private final ThriftTable table;

    Cluster cluster = null;
    Keyspace keyspace = null;

    public ThriftDDL(ThriftTable table) {
        this.table = table;

        Type<?> valueType = null;
        Type<?> rowType = null;
        Type<?> columnType = null;

        cluster.makeColumnFamilyDefinition()
            .setKeyspace(keyspace.getKeyspaceName())
            .setName(table.getName())
            .setDefaultValidationClass(valueType.getCassandraName())
            .setKeyValidationClass(rowType.getCassandraName())
            .setComparatorType(columnType.getCassandraName());
    }

    public ColumnFamilyDefinition getDDL() {
        ColumnFamilyDefinition columnFamilyDefinition = cluster.makeColumnFamilyDefinition();

        table.getElements().forEach(element -> {
            if (element instanceof Column) {
                Column column = (Column) element;
                Map<Class, Column> simpleColumn = getColumns(column);
                NamedColumn namedColumn = (NamedColumn) simpleColumn.get(NamedColumn.class);
                ThriftColumn cassandraColumn = (ThriftColumn) simpleColumn.get(ThriftColumn.class);

//                if (simpleColumn.containsKey(PartitionKeyColumn.class)) {
//                    create.addPartitionKey(namedColumn.getName(), cassandraColumn.getCassandraType());
//                } else if (simpleColumn.containsKey(ClusteringKeyColumn.class)) {
//                    create.addClusteringColumn(namedColumn.getName(), cassandraColumn.getCassandraType());
//                } else if (namedColumn != null && cassandraColumn != null) {
//                    create.addColumn(namedColumn.getName(), cassandraColumn.getCassandraType());
//                }
            }
        });

        return null;
    }

    private Map<Class, Column> getColumns(Column element) {
        return getColumns(new LinkedHashMap<>(), element);
    }

    private Map<Class, Column> getColumns(Map<Class, Column> map, Column element) {
        map.put(element.getClass(), element);

        if (element instanceof DelegateColumn) {
            getColumns(map, ((DelegateColumn) element).getColumn());
        }

        for (Class<?> iface : getInterfaces(element.getClass())) {
            if (DelegateColumn.class.equals(iface)) {
                continue;
            }

            if (Column.class.isAssignableFrom(iface)) {
                map.put(iface, element);
            }
        }
        return map;
    }

    private List<Class> getInterfaces(Class<?> clazz) {
        List<Class> interfaces = new ArrayList<>();
        do {
            Collections.addAll(interfaces, clazz.getInterfaces());
        } while ((clazz = clazz.getSuperclass()) != Object.class);
        return interfaces;
    }
}
