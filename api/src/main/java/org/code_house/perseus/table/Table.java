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
package org.code_house.perseus.table;

import org.code_house.perseus.Column;
import org.code_house.perseus.Container;
import org.code_house.perseus.dsl.Deletion;
import org.code_house.perseus.dsl.Insertion;
import org.code_house.perseus.dsl.Selection;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public interface Table<T, R> extends Container<T, R> {

    Selection<T, ? extends Column<?>> select();

    Insertion<T> insert();

    Deletion<T, ? extends Column<?>> delete();


}
