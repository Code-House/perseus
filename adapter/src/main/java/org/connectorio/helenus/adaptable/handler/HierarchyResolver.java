package org.connectorio.helenus.adaptable.handler;

import java.util.stream.Stream;

public interface HierarchyResolver {

    Stream<Class<?>> resolve(Class<?> element);
}
