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
package org.connectorio.helenus.astyanax.column;

import com.netflix.astyanax.serializers.StringSerializer;
import org.apache.cassandra.db.marshal.UTF8Type;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class StringColumn extends AbstractThriftColumn<String> {

    public StringColumn() {
        super(String.class, StringSerializer.get(), UTF8Type.class);
    }

}
