package org.connectorio.helenus.adaptable;

import org.connectorio.helenus.adaptable.handler.map.TypeMap;
import org.connectorio.helenus.adaptable.handler.resolver.CompleteHierarchyResolver;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class StaticAdaptable<T extends Adaptable<T>> implements Adaptable<T> {

    private final TypeMap<Adapter> typeMap = new TypeMap<>(new CompleteHierarchyResolver());
    private final Set<Adapter> traits = new LinkedHashSet<>();

    @Override
    public T with(Adapter adapter) {
        typeMap.put(adapter);
        return (T) this;
    }

    @Override
    public Set<Adapter> adapters() {
        return Collections.unmodifiableSet(traits);
    }

    @Override
    public <X extends Adapter> Optional<X> as(Class<X> adapterType) {
        return typeMap.get(adapterType)
            .map(Deque::getFirst);
    }
}
