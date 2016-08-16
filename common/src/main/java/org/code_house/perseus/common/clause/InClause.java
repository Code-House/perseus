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
package org.code_house.perseus.common.clause;

import org.code_house.perseus.Column;
import org.code_house.perseus.column.NamedColumn;
import org.code_house.perseus.dsl.Clause;

import java.util.List;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class InClause<J, C extends Column<J>> implements Clause<J, C, List<J>> {

    private final C column;
    private final List<J> value;

    public InClause(C column, List<J> value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public C getColumn() {
        return column;
    }

    @Override
    public List<J> getValue() {
        return value;
    }

}
