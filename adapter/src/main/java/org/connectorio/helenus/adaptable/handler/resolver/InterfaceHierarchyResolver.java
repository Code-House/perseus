package org.connectorio.helenus.adaptable.handler.resolver;

import org.connectorio.helenus.adaptable.handler.HierarchyResolver;

import java.util.Arrays;
import java.util.stream.Stream;

public class InterfaceHierarchyResolver implements HierarchyResolver {

    @Override
    public Stream<Class<?>> resolve(Class<?> element) {
        return getClassInterfaces(element);
    }

    private static Stream<Class<?>> getClassInterfaces(Class<?> clazz) {
        Stream<Class<?>> interfaces = Arrays.stream(clazz.getInterfaces());
        if (clazz.getSuperclass() != null) {
            return Stream.concat(interfaces, getClassInterfaces(clazz.getSuperclass()));
        }
        return interfaces;
    }
}
