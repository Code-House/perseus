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
package org.connectorio.helenus.dsl;

import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.Context;

import java.util.List;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public interface Selection<C extends Context, T, D extends Column<C, ?>> {

    Selection<C, T, D> where(Clause<C, ?, D, ?> caluse);

    Selection<C, T, D> limit(int limit);

    T find();

    List<T> fetch();

}
