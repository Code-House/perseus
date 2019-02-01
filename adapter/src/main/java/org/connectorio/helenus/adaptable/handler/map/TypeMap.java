package org.connectorio.helenus.adaptable.handler.map;

import org.connectorio.helenus.adaptable.handler.HierarchyResolver;

import java.util.*;

public class TypeMap<V> {

    private final HierarchyResolver hierarchyResolver;
    private Map<Class<?>, Deque<V>> typeMap = new HashMap<>();

    public TypeMap(HierarchyResolver hierarchyResolver) {
        this.hierarchyResolver = hierarchyResolver;
    }

    public void put(V value) {
        hierarchyResolver.resolve(value.getClass())
            .forEach(type -> place(value, type));
    }

    private void place(V value, Class<?> type) {
        if (!typeMap.containsKey(type)) {
            typeMap.put(type, new ArrayDeque<>());
        }
        typeMap.get(type).add(value);
    }

    public <T> Optional<Deque<T>> get(Class<T> type) {
        Deque value = typeMap.get(type);
        return Optional.ofNullable(value);
    }

}
