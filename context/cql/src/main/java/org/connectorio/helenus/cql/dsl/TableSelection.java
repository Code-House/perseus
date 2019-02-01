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
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.api.column.NamedColumn;
import org.connectorio.helenus.dsl.Clause;
import org.connectorio.helenus.dsl.Selection;
import org.connectorio.helenus.api.table.Table;
import org.connectorio.helenus.common.clause.*;

import java.util.List;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class TableSelection<C extends Context, T, D extends NamedColumn<C, ?, ?>> implements Selection<C, T, D> {

    private final Session session;
    private final Table<C> table;
    private final Select select;

    public TableSelection(Session session, Table<C> table) {
        this.session = session;
        this.table = table;
        this.select = QueryBuilder.select().all().from(table.getName());
    }

    @Override
    public Selection<C, T, D> where(Clause<C, ?, D, ?> clause) {
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
    public Selection<C, T, D> limit(int limit) {
        select.limit(limit);
        return this;
    }

    @Override
    public T find() {
        //return table.fromRow(session.execute(select).one());
        return null;
    }

    @Override
    public List<T> fetch() {
//        return session.execute(select).all().stream().map(table::fromRow).collect(Collectors.toList());
        return null;
    }
}
