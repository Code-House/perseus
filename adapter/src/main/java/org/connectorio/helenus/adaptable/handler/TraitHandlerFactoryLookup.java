package org.connectorio.helenus.adaptable.handler;

import org.connectorio.helenus.adaptable.handler.jdk.JdkTraitHandlerFactory;

import java.util.Optional;

public class TraitHandlerFactoryLookup {

    private final static Optional<TraitHandlerFactory> DEFAULT_FACTORY = cglib();
    private final static TraitHandlerFactory FALLBACK_FACTORY = new JdkTraitHandlerFactory();

    public static TraitHandlerFactory resolve() {
        return DEFAULT_FACTORY.orElse(FALLBACK_FACTORY);
    }

    private final static Optional<TraitHandlerFactory> cglib() {
        try {
            Class<?> cglibFactory = Class.forName("org.connectorio.helenus.adaptable.handler.cglib.CglibTraitHandlerFactory");
            Object factory = cglibFactory.newInstance();
            return Optional.of(factory)
                .filter(TraitHandlerFactory.class::isInstance)
                .map(TraitHandlerFactory.class::cast);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
