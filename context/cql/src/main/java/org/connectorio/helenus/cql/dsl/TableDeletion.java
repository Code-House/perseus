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
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.api.column.NamedColumn;
import org.connectorio.helenus.dsl.Clause;
import org.connectorio.helenus.dsl.Deletion;
import org.connectorio.helenus.api.table.Table;
import org.connectorio.helenus.common.clause.*;

import java.util.List;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class TableDeletion<C extends Context, T, D extends NamedColumn<C, ?, ?>> implements Deletion<C, T, D> {

    private final Session session;
    private final Table<C> table;
    private final Delete delete;

    public TableDeletion(Session session, Table<C> table) {
        this.session = session;
        this.table = table;
        this.delete = QueryBuilder.delete().all().from(table.getName());
    }

    @Override
    public Deletion<C, T, D> where(Clause<C, ?, D, ?> clause) {
        if (clause instanceof EqualsClause) {
            delete.where(QueryBuilder.eq(clause.getColumn().getName(), clause.getValue()));
        } else if (clause instanceof InClause) {
            List values = (List) clause.getValue();
            delete.where(QueryBuilder.in(clause.getColumn().getName(), values.toArray()));
        } else if (clause instanceof GreaterOrEqualsClause) {
            delete.where(QueryBuilder.gte(clause.getColumn().getName(), clause.getValue()));
        } else if (clause instanceof LessOrEqualsClause) {
            delete.where(QueryBuilder.lte(clause.getColumn().getName(), clause.getValue()));
        } else if (clause instanceof GreaterClause) {
            delete.where(QueryBuilder.gt(clause.getColumn().getName(), clause.getValue()));
        } else if (clause instanceof LessClause) {
            delete.where(QueryBuilder.lt(clause.getColumn().getName(), clause.getValue()));
        }
        return this;
    }

    @Override
    public void execute() {
        session.execute(delete);
    }


}
