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
package org.connectorio.helenus.cql.dsl;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.api.column.NamedColumn;
import org.connectorio.helenus.dsl.Insertion;
import org.connectorio.helenus.api.table.Table;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class TableInsertion<C extends Context, T> implements Insertion<C, T> {

    private final Session session;
    private final Table<C> table;
    private final Insert insert;

    public TableInsertion(Session session, Table<C> table) {
        this.session = session;
        this.table = table;
        this.insert = QueryBuilder.insertInto(table.getName());
    }

    @Override
    public <J> Insertion<C, T> insert(Column<C, J> column, J value) {
        if (!(column instanceof NamedColumn)) {
            throw new IllegalArgumentException("CQL inserts require named columns");
        }
        insert.value(((NamedColumn<C, J, ?>) column).getName(), value);
        return this;
    }

    @Override
    public void execute() {
        session.execute(insert);
    }



}
