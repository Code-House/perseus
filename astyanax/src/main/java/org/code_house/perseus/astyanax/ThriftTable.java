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
package org.code_house.perseus.astyanax;

import com.netflix.astyanax.model.ColumnList;
import org.code_house.perseus.Column;
import org.code_house.perseus.Element;
import org.code_house.perseus.dsl.Selection;
import org.code_house.perseus.table.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public abstract class ThriftTable<T> implements Table<T, ColumnList> {

    private final String name;
    private final List<Element> elements = new ArrayList<>();

    public ThriftTable(String name, Element ... elements) {
        this.name = name;

        addElements(elements);
    }

    @Override
    public void addElements(Element... elements) {
        Collections.addAll(this.elements, elements);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Element> getElements() {
        return elements;
    }

    @Override
    public Selection<T, ? extends Column<?>> select() {
        return null;
    }
}
