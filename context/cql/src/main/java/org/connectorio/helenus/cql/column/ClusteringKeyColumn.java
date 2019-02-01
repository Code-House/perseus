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
import com.datastax.driver.core.GettableByIndexData;
import com.datastax.driver.core.GettableByNameData;
import org.connectorio.helenus.adaptable.Adapter;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class ClusteringKeyColumn<J, C extends DataType> extends AbstractCassandraColumn<J, C> implements Adapter {

    private final CassandraColumn<J, C> column;

    public ClusteringKeyColumn(CassandraColumn<J, C> column) {
        this.column = column;
    }

    //@Override
    public J fromData(String name, GettableByNameData data) {
        return null; //column.fromData(name, data);
    }

    //@Override
    public J fromData(int index, GettableByIndexData data) {
        return null; // column.fromData(index, data);
    }

    @Override
    public C getCassandraType() {
        return column.getCassandraType();
    }

    //@Override
    public CassandraColumn<J, C> getColumn() {
        return column;
    }

    @Override
    public Class<J> getJavaType() {
        return column.getJavaType();
    }

}
