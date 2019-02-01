package org.connectorio.helenus.cql.struct;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.connectorio.helenus.api.column.NamedColumn;
import org.connectorio.helenus.cql.CassandraCQLContext;
import org.connectorio.helenus.cql.CassandraCQLTable;
import org.connectorio.helenus.cql.column.NamedCassandraColumn;
import org.connectorio.helenus.dsl.Selection;
import org.connectorio.helenus.cql.column.Builder;

import java.util.UUID;

public class UsersTable extends CassandraCQLTable<User> {

    private final NamedCassandraColumn<UUID, ?> id = Builder.uuid().partitionKey().named("id").build();
    private final NamedCassandraColumn<String, ?> name = Builder.string().named("name").build();

    public UsersTable(Session session) {
        super("users", session);

        addElements(id, name);
    }

    public User fromRow(Row row) {
        return new User(row.getUUID(id.getName()), row.getString(name.getName()));
    }

    public Selection<CassandraCQLContext, User, NamedColumn<CassandraCQLContext, ?, ?>> byId(UUID uuid) {
        return null; //select().where(new EqualsClause(id, uuid));
    }

    @Override
    public void bind(CassandraCQLContext context) {

    }
}