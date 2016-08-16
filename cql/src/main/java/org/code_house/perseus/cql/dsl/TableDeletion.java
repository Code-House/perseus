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
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.code_house.perseus.column.NamedColumn;
import org.code_house.perseus.common.clause.*;
import org.code_house.perseus.dsl.Clause;
import org.code_house.perseus.dsl.Deletion;
import org.code_house.perseus.table.Table;

import java.util.List;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class TableDeletion<T> implements Deletion<T, NamedColumn<?, ?>> {

    private final Session session;
    private final Table<T, Row> table;
    private final Delete delete;

    public TableDeletion(Session session, Table<T, Row> table) {
        this.session = session;
        this.table = table;
        this.delete = QueryBuilder.delete().all().from(table.getName());
    }

    @Override
    public Deletion<T, NamedColumn<?, ?>> where(Clause<?, NamedColumn<?, ?>, ?> clause) {
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
