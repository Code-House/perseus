package org.connectorio.helenus.visitor;

import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.Context;

public interface VisitableColumn<C extends Context, J> extends Column<C, J> {

    void visit(ElementVisitor visitor);

}
