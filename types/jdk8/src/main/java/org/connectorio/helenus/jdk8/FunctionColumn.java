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
package org.connectorio.helenus.jdk8;

import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.mapping.MappedColumn;
import org.connectorio.helenus.adaptable.StaticAdaptable;

import java.util.function.Function;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class FunctionColumn<C extends Context, T, J, D extends Column<C, J>> extends StaticAdaptable implements MappedColumn<C, T, J, D> {

    private final D column;
    private final Function<T, J> function;

    public FunctionColumn(D column, Function<T, J> function) {
        this.column = column;
        this.function = function;
    }

    @Override
    public D getColumn() {
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

    @Override
    public void bind(C context) {
        column.bind(context);
    }
}
