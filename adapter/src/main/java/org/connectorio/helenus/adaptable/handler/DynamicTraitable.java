package org.connectorio.helenus.adaptable.handler;

import org.connectorio.helenus.adaptable.Adapter;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public interface DynamicTraitable {

    default <T extends DynamicTraitable> T with(Adapter trait) {
        return TraitHandlerFactoryLookup.resolve().create(this, trait);
    }

    default Set<Adapter> traits() {
        return Collections.emptySet();
    }

    default <T extends Adapter> Optional<T> as(Class<T> trait) {
        return Optional.empty();
    }

}
