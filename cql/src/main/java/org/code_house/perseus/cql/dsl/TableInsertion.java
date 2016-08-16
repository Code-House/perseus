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
package org.code_house.perseus.cql.dsl;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.code_house.perseus.Column;
import org.code_house.perseus.column.NamedColumn;
import org.code_house.perseus.dsl.Insertion;
import org.code_house.perseus.table.Table;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class TableInsertion<T> implements Insertion<T> {

    private final Session session;
    private final Table<T, Row> table;
    private final Insert insert;

    public TableInsertion(Session session, Table<T, Row> table) {
        this.session = session;
        this.table = table;
        this.insert = QueryBuilder.insertInto(table.getName());
    }

    @Override
    public <J> Insertion<T> insert(Column<J> column, J value) {
        if (!(column instanceof NamedColumn)) {
            throw new IllegalArgumentException("CQL inserts require named columns");
        }
        insert.value(((NamedColumn<J, ?>) column).getName(), value);
        return this;
    }

    @Override
    public void execute() {
        session.execute(insert);
    }



}
