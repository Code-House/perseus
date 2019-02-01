package org.connectorio.helenus.adaptable.handler.resolver;

import org.connectorio.helenus.adaptable.handler.HierarchyResolver;

import java.util.stream.Stream;

public class CompleteHierarchyResolver implements HierarchyResolver {

    @Override
    public Stream<Class<?>> resolve(Class<?> element) {
        return getClassHierarchy(element);
    }

    private static Stream<Class<?>> getClassHierarchy(Class<?> clazz) {
        Stream<Class<?>> interfaces = Stream.concat(Stream.of(clazz), Stream.of(clazz.getInterfaces()));
        if (clazz.getSuperclass() != null) {
            return Stream.concat(interfaces, getClassHierarchy(clazz.getSuperclass()));
        }
        return interfaces;
    }
}
