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
import org.connectorio.helenus.cql.CassandraCQLContext;
import org.connectorio.helenus.adaptable.Adapter;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class PartitionKeyColumn<J, D extends DataType> extends AbstractCassandraColumn<J, D>
    implements DelegateColumn<CassandraCQLContext, J, CassandraColumn<J, D>>, CassandraColumn<J, D>, Adapter {

    private final CassandraColumn<J, D> column;

    public PartitionKeyColumn(CassandraColumn<J, D> column) {
        this.column = column;
    }

    @Override
    public D getCassandraType() {
        return column.getCassandraType();
    }

    @Override
    public CassandraColumn<J, D> getColumn() {
        return column;
    }

    @Override
    public Class<J> getJavaType() {
        return column.getJavaType();
    }

    @Override
    public void bind(CassandraCQLContext context) {

    }

}
