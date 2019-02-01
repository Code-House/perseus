package org.connectorio.helenus.visitor.core;

import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.api.Element;
import org.connectorio.helenus.api.Index;
import org.connectorio.helenus.visitor.ElementVisitor;

public class ElementVisitorSupport<C extends Context> implements ElementVisitor<C> {


    @Override
    public void visit(Element element) {
    }

    @Override
    public void visit(Index index) {
    }

    @Override
    public void visit(Column<C, ?> column) {
    }

}
