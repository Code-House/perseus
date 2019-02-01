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
package org.connectorio.helenus.cql.column;

import com.datastax.driver.core.DataType;
import org.connectorio.helenus.api.column.DelegateColumn;
import org.connectorio.helenus.api.column.NamedColumn;
import org.connectorio.helenus.cql.CassandraCQLContext;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class NamedCassandraColumn<J, C extends DataType> extends AbstractCassandraColumn<J, C> implements
    NamedColumn<CassandraCQLContext, J, CassandraColumn<J, C>>,
        DelegateColumn<CassandraCQLContext, J, CassandraColumn<J, C>>,
    CassandraColumn<J, C> {

    private final String name;
    private final CassandraColumn<J, C> column;

    public NamedCassandraColumn(String name, CassandraColumn<J, C> column) {
        this.name = name;
        this.column = column;
    }

    @Override
    public String getName() {
        return name;
    }

//    public J fromRow(Row row) {
//        return column.fromData(name, row);
//    }

//    @Override
//    public J fromData(String name, GettableByNameData data) {
//        return column.fromData(name, data);
//    }
//
//    @Override
//    public J fromData(int index, GettableByIndexData data) {
//        return column.fromData(index, data);
//    }

    @Override
    public C getCassandraType() {
        return column.getCassandraType();
    }

    @Override
    public Class<J> getJavaType() {
        return column.getJavaType();
    }

    @Override
    public CassandraColumn<J, C> getColumn() {
        return column;
    }


}
