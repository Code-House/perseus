package org.connectorio.helenus.adaptable.handler.cglib;

import org.connectorio.helenus.adaptable.Adapter;
import org.connectorio.helenus.adaptable.handler.DynamicTraitable;
import org.connectorio.helenus.adaptable.handler.map.TypeMap;

import java.util.Deque;
import java.util.Optional;
import java.util.Set;

public class TraitStub {

    private final DynamicTraitable traitable;
    private final Set<Adapter> traits;
    private final TypeMap<Adapter> typeMap;

    public TraitStub(DynamicTraitable traitable, Set<Adapter> traits, TypeMap<Adapter> typeMap) {
        this.traitable = traitable;
        this.traits = traits;
        this.typeMap = typeMap;
    }

    public <T extends Adapter> Optional<T> as(Class<T> trait) {
        return typeMap.get(trait).map(Deque::getFirst);
    }

    public Set<Adapter> traits() {
        return traits;
    }

    @Override
    public String toString() {
        return traitable.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return traitable.equals(obj);
    }

    @Override
    public int hashCode() {
        return traitable.hashCode();
    }
}
