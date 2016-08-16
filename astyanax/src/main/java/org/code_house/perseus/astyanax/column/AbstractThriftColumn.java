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
package org.code_house.perseus.astyanax.column;

import com.netflix.astyanax.Serializer;
import org.code_house.perseus.astyanax.ThriftColumn;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class AbstractThriftColumn<J> implements ThriftColumn<J> {

    private final Class<J> javaType;
    private final Serializer<J> serializer;
    private final Class<?> cassandraType;

    protected AbstractThriftColumn(Class<J> javaType, Serializer<J> serializer, Class<?> cassandraType) {
        this.javaType = javaType;
        this.serializer = serializer;
        this.cassandraType = cassandraType;
    }

    @Override
    public Serializer<J> getSerializer() {
        return serializer;
    }

    @Override
    public String getCassandraTypeName() {
        return cassandraType.getSimpleName();
    }

    @Override
    public Class<J> getJavaType() {
        return javaType;
    }

}
