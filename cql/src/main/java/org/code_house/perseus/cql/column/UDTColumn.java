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

import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UserType;
import org.code_house.perseus.Column;
import org.code_house.perseus.column.NamedColumn;

import java.util.Arrays;
import java.util.List;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public abstract class UDTColumn<J> extends CompositeColumn<J> implements NamedColumn<J, CassandraColumn<J, UserType>>, CassandraColumn<J, UserType>,
    LazyColumn<J> {

    private final String name;
    private final String typeName;
    private final Class<J> type;
    private final List<Column> columns;
    private UserType cassandraType;

    public UDTColumn(String name, String typeName, Class<J> type, Column... columns) {
        this.name = name;
        this.typeName = typeName;
        this.type = type;
        this.columns = Arrays.asList(columns);
    }

    @Override
    public Class<J> getJavaType() {
        return type;
    }

    @Override
    public UserType getCassandraType() {
        return cassandraType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void bind(Session session) {
        KeyspaceMetadata keyspace = session.getCluster().getMetadata().getKeyspace(session.getLoggedKeyspace());
        this.cassandraType = keyspace.getUserType(typeName);
    }
}
