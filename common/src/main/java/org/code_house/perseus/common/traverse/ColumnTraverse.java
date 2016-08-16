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
package org.code_house.perseus.common.traverse;

import org.code_house.perseus.Column;
import org.code_house.perseus.column.WrapperColumn;

import java.util.*;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class ColumnTraverse {

    public Map<Class, Column> getColumns(Column element) {
        return getColumns(new LinkedHashMap<>(), element);
    }

    public Map<Class, Column> getColumns(Map<Class, Column> map, Column element) {
        map.put(element.getClass(), element);

        if (element instanceof WrapperColumn) {
            getColumns(map, ((WrapperColumn) element).getColumn());
        }

        for (Class<?> iface : getInterfaces(element.getClass())) {
            if (WrapperColumn.class.equals(iface)) {
                continue;
            }

            if (Column.class.isAssignableFrom(iface)) {
                map.put(iface, element);
            }
        }
        return map;
    }

    private List<Class> getInterfaces(Class<?> clazz) {
        List<Class> interfaces = new ArrayList<>();
        do {
            Collections.addAll(interfaces, clazz.getInterfaces());
        } while ((clazz = clazz.getSuperclass()) != Object.class);
        return interfaces;
    }

}
