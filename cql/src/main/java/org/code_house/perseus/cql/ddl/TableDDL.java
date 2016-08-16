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
package org.code_house.perseus.cql.ddl;

import com.datastax.driver.core.schemabuilder.Create;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import org.code_house.perseus.Column;
import org.code_house.perseus.column.*;
import org.code_house.perseus.common.traverse.ColumnTraverse;
import org.code_house.perseus.cql.column.CassandraColumn;
import org.code_house.perseus.cql.column.ClusteringKeyColumn;
import org.code_house.perseus.cql.column.PartitionKeyColumn;
import org.code_house.perseus.table.Table;

import java.util.*;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class TableDDL {

    private final Table table;

    public TableDDL(Table table) {
        this.table = table;
    }

    public Create getDDL() {
        Create create = SchemaBuilder.createTable(table.getName());

        final ColumnTraverse columnTraverse = new ColumnTraverse();

        table.getElements().forEach(element -> {
            if (element instanceof Column) {
                processColumn(create, columnTraverse, (Column) element);
            }
        });

        return create;
    }

    private void processColumn(Create create, ColumnTraverse columnTraverse, Column element) {
        Column column = element;
        Map<Class, Column> simpleColumn = columnTraverse.getColumns(column);
        NamedColumn namedColumn = (NamedColumn) simpleColumn.get(NamedColumn.class);
        CassandraColumn cassandraColumn = (CassandraColumn) simpleColumn.get(CassandraColumn.class);
        if (simpleColumn.containsKey(PartitionKeyColumn.class)) {
            create.addPartitionKey(namedColumn.getName(), cassandraColumn.getCassandraType());
        } else if (simpleColumn.containsKey(ClusteringKeyColumn.class)) {
            create.addClusteringColumn(namedColumn.getName(), cassandraColumn.getCassandraType());
        } else if (namedColumn != null && cassandraColumn != null) {
            create.addColumn(namedColumn.getName(), cassandraColumn.getCassandraType());
        } else if (simpleColumn.containsKey(CompoundColumn.class)) {
            ((CompoundColumn) simpleColumn.get(CompoundColumn.class)).getColumns().forEach(col -> {
                if (col instanceof Column) {
                    processColumn(create, columnTraverse, (Column) col);
                }
            });
        }
    }

}
