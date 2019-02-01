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
package org.connectorio.helenus.cql.column;

import com.datastax.driver.core.DataType;
import org.connectorio.helenus.adaptable.StaticAdaptable;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
abstract class AbstractCassandraColumn<J, C extends DataType, T extends AbstractCassandraColumn<J, C, T>> extends StaticAdaptable<T> implements CassandraColumn<J, C> {

}
