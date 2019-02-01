package org.connectorio.helenus.visitor.core;

import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.api.table.Table;
import org.connectorio.helenus.visitor.ElementVisitor;

public class TableVisitor<C extends Context> {

    private final ElementVisitor<C> visitor;

    public TableVisitor(ElementVisitor<C> visitor) {
        this.visitor = visitor;
    }

    void visit(Table<C> table) {
        table.getElements().forEach(visitor::visit);
    }

}
