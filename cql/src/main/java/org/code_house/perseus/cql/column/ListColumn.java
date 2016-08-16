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

import java.util.List;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class ListColumn<J> extends CollectionColumn<List<J>> {

    private final Class<J> valueType;

    public ListColumn(CassandraColumn<J, ?> column) {
        super(DataType.list(column.getCassandraType()));
        this.valueType = column.getJavaType();
    }

    @Override
    public List<J> fromData(String name, GettableByNameData data) {
        return data.getList(name, valueType);
    }

    @Override
    public List<J> fromData(int index, GettableByIndexData data) {
        return data.getList(index, valueType);
    }

    @Override
    public Class<List<J>> getJavaType() {
        return (Class<List<J>>) (Object) List.class;
    }

}
