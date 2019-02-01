package org.connectorio.helenus.adaptable;

import java.util.Optional;
import java.util.Set;

/**
 * Addi
 *
 * @param <T> Type of parent who implements this type.
 */
public interface Adaptable<T extends Adaptable<?>> {

    T with(Adapter adapter);

    <X extends Adapter> Optional<X> as(Class<X> adoptedType);

    Set<Adapter> adapters();
}
