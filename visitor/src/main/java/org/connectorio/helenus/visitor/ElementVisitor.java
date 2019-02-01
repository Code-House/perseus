package org.connectorio.helenus.visitor;

import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.api.Element;
import org.connectorio.helenus.api.Index;

public interface ElementVisitor<C extends Context> {

    void visit(Element element);

    void visit(Index index);

    void visit(Column<C, ?> column);

}
