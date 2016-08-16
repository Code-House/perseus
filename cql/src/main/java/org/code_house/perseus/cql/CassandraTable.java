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
package org.code_house.perseus.cql;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.code_house.perseus.Element;
import org.code_house.perseus.column.NamedColumn;
import org.code_house.perseus.cql.dsl.TableDeletion;
import org.code_house.perseus.cql.dsl.TableInsertion;
import org.code_house.perseus.cql.dsl.TableSelection;
import org.code_house.perseus.table.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public abstract class CassandraTable<T> implements Table<T, Row> {

    private final String name;
    private final Session session;
    private final List<Element> elements = new ArrayList<>();

    public CassandraTable(String name, Session session) {
        this.name = name;
        this.session = session;
    }

    public CassandraTable(String name, Session session, Element ... elements) {
        this(name, session);
        addElements(elements);
    }

    public String getName() {
        return name;
    }

    public List<Element> getElements() {
        return elements;
    }

    @Override
    public void addElements(Element... elements) {
        Collections.addAll(this.elements, elements);
    }

    @Override
    public TableSelection<T> select() {
        return new TableSelection<T>(session, this);
    }

    @Override
    public TableInsertion<T> insert() {
        return new TableInsertion<>(session, this);
    }

    public TableDeletion<T> delete() {
        return new TableDeletion<T>(session, this);
    }
}
