package org.connectorio.helenus.cql.struct;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.connectorio.helenus.cql.CassandraCQLContext;
import org.connectorio.helenus.cql.CassandraCQLTable;
import org.connectorio.helenus.cql.column.ClusteringKeyColumn;
import org.connectorio.helenus.cql.column.NamedCassandraColumn;
import org.connectorio.helenus.cql.column.PartitionKeyColumn;
import org.connectorio.helenus.cql.column.StringColumn;
import org.connectorio.helenus.cql.column.TimeUUIDColumn;
import org.connectorio.helenus.cql.column.UUIDColumn;

import java.util.UUID;

public class UserActionsTable extends CassandraCQLTable<User> {

    private final NamedCassandraColumn<UUID, ?> id = new NamedCassandraColumn("id", new PartitionKeyColumn(new UUIDColumn()));
    private final NamedCassandraColumn<UUID, ?> time = new NamedCassandraColumn("time", new ClusteringKeyColumn(new TimeUUIDColumn()));
    private final NamedCassandraColumn<String, ?> action = new NamedCassandraColumn("action", new StringColumn());

    public UserActionsTable(Session session) {
        super("user_actions", session);
        addElements(id, time, action);
    }

    public User fromRow(Row row) {
        return new User(row.getUUID(id.getName()), row.getString(action.getName()));
    }

    @Override
    public void bind(CassandraCQLContext context) {

    }
}