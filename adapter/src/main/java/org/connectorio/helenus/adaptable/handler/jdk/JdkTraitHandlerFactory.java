package org.connectorio.helenus.adaptable.handler.jdk;

import org.connectorio.helenus.adaptable.Adapter;
import org.connectorio.helenus.adaptable.handler.DynamicTraitable;
import org.connectorio.helenus.adaptable.handler.handle.JdkTraitHandler;
import org.connectorio.helenus.adaptable.handler.TraitHandlerFactory;

import java.util.Set;

public class JdkTraitHandlerFactory implements TraitHandlerFactory {

    @Override
    public <T extends DynamicTraitable> T create(DynamicTraitable traitable, Set<Adapter> traits) {
        return JdkTraitHandler.create(traitable, traits);
    }

}
