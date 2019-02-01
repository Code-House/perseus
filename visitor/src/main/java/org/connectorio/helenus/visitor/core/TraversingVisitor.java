package org.connectorio.helenus.visitor.core;

import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.api.Element;
import org.connectorio.helenus.api.Index;
import org.connectorio.helenus.api.column.CompoundColumn;
import org.connectorio.helenus.api.column.DelegateColumn;
import org.connectorio.helenus.visitor.ElementVisitor;

public class TraversingVisitor<C extends Context> implements ElementVisitor<C> {

    private final ElementVisitor<C> delegate;

    public TraversingVisitor(ElementVisitor<C> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void visit(Element element) {

    }

    @Override
    public void visit(Index index) {

    }

    @Override
    public void visit(Column<C, ?> column) {
        if (column instanceof DelegateColumn) {
            visit(((DelegateColumn) column).getColumn());
        } else if (column instanceof CompoundColumn) {
            ((CompoundColumn<C, ?>) column).getColumns().forEach(this::visit);
        } else {
            visit(column);
        }

    }
}
