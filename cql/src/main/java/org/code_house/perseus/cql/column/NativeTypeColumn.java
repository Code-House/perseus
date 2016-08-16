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

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.GettableByIndexData;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public abstract class NativeTypeColumn<J, C extends DataType.NativeType> implements CassandraColumn<J, C> {

    private final C cassandraType;

    protected NativeTypeColumn(C cassandraType) {
        this.cassandraType = cassandraType;
    }

    @Override
    public C getCassandraType() {
        return cassandraType;
    }

}
