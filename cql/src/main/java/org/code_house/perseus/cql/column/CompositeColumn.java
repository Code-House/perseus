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

import com.datastax.driver.core.GettableData;
import org.code_house.perseus.Column;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * An column which is wrapping multiple columns.
 *
 * It is mapped to one type but may cover multiple columns under the hood.
 *
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public abstract class CompositeColumn<J> implements Column<J> {

    private final List<Column<?>> columns;

    public CompositeColumn(Column<?> ... columns) {
        this.columns = Arrays.asList(columns);
    }

//    public J fromData(GettableData data) {
//        List<Object> values = new LinkedList();
//        for (Column<?> column : columns) {
//            values.add(column.fromData(data));
//        }
//        return create(values);
//    }

    protected abstract J create(List<Object> values);


}
