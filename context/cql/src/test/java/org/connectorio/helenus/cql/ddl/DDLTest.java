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
package org.connectorio.helenus.cql.ddl;

import org.connectorio.helenus.cql.struct.UserActionsTable;
import org.connectorio.helenus.cql.struct.UsersTable;
import org.connectorio.helenus.cql.struct.TweetsTable;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class DDLTest {

    public static void main(String args[]) {
        TableDDL ddl = new TableDDL(new UsersTable(null));
        System.out.println(ddl.getDDL());

        ddl = new TableDDL(new UserActionsTable(null));
        System.out.println(ddl.getDDL());

        ddl = new TableDDL(new TweetsTable(null));
        System.out.println(ddl.getDDL());
    }

}
