package org.connectorio.helenus.cql.struct;

import com.datastax.driver.core.Session;
import org.connectorio.helenus.cql.CassandraCQLContext;
import org.connectorio.helenus.cql.CassandraCQLTable;
import org.connectorio.helenus.cql.column.NamedCassandraColumn;
import org.connectorio.helenus.cql.column.PartitionKeyColumn;
import org.connectorio.helenus.cql.column.StringColumn;
import org.connectorio.helenus.cql.column.Builder;

import java.util.UUID;

public class TweetsTable extends CassandraCQLTable<Tweet> {

    private final NamedCassandraColumn<String, ?> user = new NamedCassandraColumn<>("user", new PartitionKeyColumn<>(new StringColumn()));
    private final NamedCassandraColumn<UUID, ?> id = Builder.uuid().named("id").build();
    private final TimeBucketColumn date = new TimeBucketColumn();
    private final NamedCassandraColumn<String, ?> tweet = new NamedCassandraColumn<>("tweet", new StringColumn());

    public TweetsTable(Session session) {
        super("tweets", session);

        addElements(user, id, date, tweet);
    }

    @Override
    public void bind(CassandraCQLContext context) {

    }

//    public Tweet fromRow(Row row) {
//        return new Tweet(id.fromRow(row), date.fromRow(row), user.fromRow(row), tweet.fromRow(row));
//    }

}