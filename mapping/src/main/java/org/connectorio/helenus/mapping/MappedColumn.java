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
package org.connectorio.helenus.mapping;

import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.api.column.DelegateColumn;

/**
 * Column which accepts complex object but stores just single field of it.
 *
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public interface MappedColumn<C extends Context, T, J, D extends Column<C, J>> extends DelegateColumn<C, J, D> {

    J fromObject(T object);

}

