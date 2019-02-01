package org.connectorio.helenus.adaptable.handler.resolver;

import org.connectorio.helenus.adaptable.handler.HierarchyResolver;

import java.util.stream.Stream;

public class NoopHierarchyResolver implements HierarchyResolver {

    @Override
    public Stream<Class<?>> resolve(Class<?> element) {
        return Stream.of(element);
    }
}
