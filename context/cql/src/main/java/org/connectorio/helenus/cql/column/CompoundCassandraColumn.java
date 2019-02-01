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
package org.connectorio.helenus.cql.column;

import org.connectorio.helenus.api.column.CompoundColumn;
import org.connectorio.helenus.cql.CassandraCQLContext;
import org.connectorio.helenus.adaptable.StaticAdaptable;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public abstract class CompoundCassandraColumn<J, T> extends StaticAdaptable implements CompoundColumn<CassandraCQLContext, J> {

    private final Class<J> type;

    public CompoundCassandraColumn(Class<J> type) {
        this.type = type;
    }

    protected abstract J fromObjectInternal(T object);

//    @Override
    public J fromObject(T object) {
        J value = fromObjectInternal(object);

        return value;
    }

    @Override
    public Class<J> getJavaType() {
        return type;
    }

}
