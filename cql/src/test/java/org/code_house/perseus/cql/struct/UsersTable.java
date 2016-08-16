package org.code_house.perseus.cql.struct;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.code_house.perseus.column.NamedColumn;
import org.code_house.perseus.common.clause.EqualsClause;
import org.code_house.perseus.cql.CassandraTable;
import org.code_house.perseus.cql.column.NamedCassandraColumn;
import org.code_house.perseus.dsl.Selection;

import static org.code_house.perseus.cql.column.Builder.*;

import java.util.UUID;

public class UsersTable extends CassandraTable<User> {

    private final NamedCassandraColumn<UUID, ?> id = uuid().partitionKey().named("id").build();
    private final NamedCassandraColumn<String, ?> name = string().named("name").build();

    public UsersTable(Session session) {
        super("users", session);

        addElements(id, name);
    }

    public User fromRow(Row row) {
        return new User(id.fromRow(row), name.fromRow(row));
    }

    public Selection<User, NamedColumn<?, ?>> byId(UUID uuid) {
        return select().where(new EqualsClause(id, uuid));
    }

}