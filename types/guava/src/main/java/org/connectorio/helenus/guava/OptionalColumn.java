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
package org.connectorio.helenus.guava;

import com.google.common.base.Optional;
import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.adaptable.StaticAdaptable;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class OptionalColumn<C extends Context, J> extends StaticAdaptable implements Column<C, Optional<J>> {

    private final Column<C, J> column;

    public OptionalColumn(Column<C, J> column) {
        this.column = column;
    }

    public java.util.Optional<J> get() {
        return java.util.Optional.ofNullable(null /*column.read*/);
    }

    @Override
    public Class<Optional<J>> getJavaType() {
        return (Class<Optional<J>>) (Object) Optional.class;
    }

    @Override
    public void bind(C context) {
        column.bind(context);
    }
}
