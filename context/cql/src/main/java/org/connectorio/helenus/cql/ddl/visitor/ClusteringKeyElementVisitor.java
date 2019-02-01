package org.connectorio.helenus.cql.ddl.visitor;

import com.datastax.driver.core.schemabuilder.Create;
import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.column.NamedColumn;
import org.connectorio.helenus.cql.CassandraCQLContext;
import org.connectorio.helenus.cql.column.ClusteringKeyColumn;
import org.connectorio.helenus.visitor.core.ElementVisitorSupport;

import java.util.Optional;

public class ClusteringKeyElementVisitor extends ElementVisitorSupport<CassandraCQLContext> {

    private final Create statement;

    public ClusteringKeyElementVisitor(Create statement) {
        this.statement = statement;
    }

    @Override
    public void visit(Column<CassandraCQLContext, ?> column) {
        Optional<ClusteringKeyColumn> key = column.as(ClusteringKeyColumn.class);
        Optional<NamedColumn> named = column.as(NamedColumn.class);

        if (key.isPresent() && named.isPresent()) {
            statement.addClusteringColumn(named.get().getName(), key.get().getCassandraType());
        }
    }
}
