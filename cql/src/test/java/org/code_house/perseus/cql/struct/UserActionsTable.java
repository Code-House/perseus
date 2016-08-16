package org.code_house.perseus.cql.struct;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.code_house.perseus.cql.CassandraTable;
import org.code_house.perseus.cql.column.*;

import java.util.UUID;

public class UserActionsTable extends CassandraTable<User> {

    private final NamedCassandraColumn<UUID, ?> id = new NamedCassandraColumn("id", new PartitionKeyColumn(new UUIDColumn()));
    private final NamedCassandraColumn<UUID, ?> time = new NamedCassandraColumn("time", new ClusteringKeyColumn(new TimeUUIDColumn()));
    private final NamedCassandraColumn<String, ?> action = new NamedCassandraColumn("action", new StringColumn());

    public UserActionsTable(Session session) {
        super("user_actions", session);
        addElements(id, time, action);
    }

    public User fromRow(Row row) {
        return new User(id.fromRow(row), action.fromRow(row));
    }

}