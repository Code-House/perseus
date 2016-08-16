package org.code_house.perseus.cql.struct;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.code_house.perseus.cql.CassandraTable;
import org.code_house.perseus.cql.column.NamedCassandraColumn;
import org.code_house.perseus.cql.column.PartitionKeyColumn;
import org.code_house.perseus.cql.column.StringColumn;

import java.util.UUID;

import static org.code_house.perseus.cql.column.Builder.uuid;

public class TweetsTable extends CassandraTable<Tweet> {

    private final NamedCassandraColumn<String, ?> user = new NamedCassandraColumn<>("user", new PartitionKeyColumn<>(new StringColumn()));
    private final NamedCassandraColumn<UUID, ?> id = uuid().named("id").build();
    private final TimeBucketColumn date = new TimeBucketColumn();
    private final NamedCassandraColumn<String, ?> tweet = new NamedCassandraColumn<>("tweet", new StringColumn());

    public TweetsTable(Session session) {
        super("tweets", session);

        addElements(user, id, date, tweet);
    }

    public Tweet fromRow(Row row) {
        return new Tweet(id.fromRow(row), date.fromRow(row), user.fromRow(row), tweet.fromRow(row));
    }

}