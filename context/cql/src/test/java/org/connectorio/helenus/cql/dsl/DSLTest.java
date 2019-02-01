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
package org.connectorio.helenus.cql.dsl;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.connectorio.helenus.cql.struct.UsersTable;

import java.util.UUID;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class DSLTest {

    public static void main(String args[]) {
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect("test");

        /*
        new TableInsertion<>(session, new UsersTable())
                .insert(UsersTable.id, UUID.randomUUID())
                .insert(UsersTable.name, "Johny Bravo")
                .execute();
        */

        UsersTable users = new UsersTable(session);
        //TableSelection<User> selection = users.select();
        //List<User> fetch = selection.fetch();

//        for (User user : fetch) {
//            System.out.println(user.getId() + " " + user.getName());
//        }

        System.out.println(users.byId(UUID.fromString("18dc4270-f73c-419b-88cf-795f73aadc5f")).find());
    }



}
