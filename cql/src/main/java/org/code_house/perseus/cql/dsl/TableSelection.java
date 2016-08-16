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
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.code_house.perseus.column.NamedColumn;
import org.code_house.perseus.common.clause.*;
import org.code_house.perseus.cql.column.NamedCassandraColumn;
import org.code_house.perseus.dsl.Clause;
import org.code_house.perseus.dsl.Selection;
import org.code_house.perseus.table.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class TableSelection<T> implements Selection<T, NamedColumn<?, ?>> {

    private final Session session;
    private final Table<T, Row> table;
    private final Select select;

    public TableSelection(Session session, Table<T, Row> table) {
        this.session = session;
        this.table = table;
        this.select = QueryBuilder.select().all().from(table.getName());
    }

    @Override
    public Selection<T, NamedColumn<?, ?>> where(Clause<?, NamedColumn<?, ?>, ?> clause) {
        if (clause instanceof EqualsClause) {
            select.where(QueryBuilder.eq(clause.getColumn().getName(), clause.getValue()));
        } else if (clause instanceof InClause) {
            List values = (List) clause.getValue();
            select.where(QueryBuilder.in(clause.getColumn().getName(), values.toArray()));
        } else if (clause instanceof GreaterOrEqualsClause) {
            select.where(QueryBuilder.gte(clause.getColumn().getName(), clause.getValue()));
        } else if (clause instanceof LessOrEqualsClause) {
            select.where(QueryBuilder.lte(clause.getColumn().getName(), clause.getValue()));
        } else if (clause instanceof GreaterClause) {
            select.where(QueryBuilder.gt(clause.getColumn().getName(), clause.getValue()));
        } else if (clause instanceof LessClause) {
            select.where(QueryBuilder.lt(clause.getColumn().getName(), clause.getValue()));
        }
        return this;
    }

    @Override
    public Selection<T, NamedColumn<?, ?>> limit(int limit) {
        select.limit(limit);
        return this;
    }

    @Override
    public T find() {
        return table.fromRow(session.execute(select).one());
    }

    @Override
    public List<T> fetch() {
        return session.execute(select).all().stream().map(table::fromRow).collect(Collectors.toList());
    }
}
