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
package org.code_house.perseus.jdk8;

import org.code_house.perseus.Column;

import java.util.function.Function;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class MappedColumn<T, J, C extends Column<J>> implements org.code_house.perseus.column.MappedColumn<T, J, C> {

    private final C column;
    private final Function<T, J> function;

    public MappedColumn(C column, Function<T, J> function) {
        this.column = column;
        this.function = function;
    }

    @Override
    public C getColumn() {
        return column;
    }

    @Override
    public J fromObject(T object) {
        return function.apply(object);
    }

    @Override
    public Class<J> getJavaType() {
        return column.getJavaType();
    }

}
