package org.connectorio.helenus.adaptable.handler;

import org.connectorio.helenus.adaptable.Adapter;

import java.util.Collections;
import java.util.Set;

public interface TraitHandlerFactory {

    default <T extends DynamicTraitable> T create(DynamicTraitable traitable, Adapter trait) {
        return create(traitable, Collections.singleton(trait));
    }

    <T extends DynamicTraitable> T create(DynamicTraitable traitable, Set<Adapter> trait);

}
