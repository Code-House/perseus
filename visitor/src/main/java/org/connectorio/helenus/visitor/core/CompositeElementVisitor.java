package org.connectorio.helenus.visitor.core;

import org.connectorio.helenus.api.Column;
import org.connectorio.helenus.api.Context;
import org.connectorio.helenus.api.Element;
import org.connectorio.helenus.api.Index;
import org.connectorio.helenus.visitor.ElementVisitor;

import java.util.Arrays;
import java.util.List;

public class CompositeElementVisitor<C extends Context> implements ElementVisitor<C> {

    private List<ElementVisitor<C>> visitors;

    public CompositeElementVisitor(ElementVisitor<C> ... visitors) {
        this(Arrays.asList(visitors));
    }

    public CompositeElementVisitor(List<ElementVisitor<C>> visitors) {
        this.visitors = visitors;
    }

    @Override
    public void visit(Element element) {
        visitors.forEach(visitor -> visitor.visit(element));
    }

    @Override
    public void visit(Index index) {
        visitors.forEach(visitor -> visitor.visit(index));
    }

    @Override
    public void visit(Column<C, ?> column) {
        visitors.forEach(visitor -> visitor.visit(column));
    }

}
