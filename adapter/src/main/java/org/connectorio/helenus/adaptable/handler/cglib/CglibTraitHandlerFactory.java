package org.connectorio.helenus.adaptable.handler.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Mixin;
import org.connectorio.helenus.adaptable.Adapter;
import org.connectorio.helenus.adaptable.Adaptable;
import org.connectorio.helenus.adaptable.handler.DynamicTraitable;
import org.connectorio.helenus.adaptable.handler.HierarchyResolver;
import org.connectorio.helenus.adaptable.handler.TraitHandlerFactory;
import org.connectorio.helenus.adaptable.handler.map.TypeMap;
import org.connectorio.helenus.adaptable.handler.resolver.CompleteHierarchyResolver;
import org.connectorio.helenus.adaptable.handler.resolver.InterfaceHierarchyResolver;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CglibTraitHandlerFactory implements TraitHandlerFactory {

    //@Override
    public <T extends DynamicTraitable> T create(DynamicTraitable traitable, Set<Adapter> traits) {

        Mixin.Generator gen = new Mixin.Generator();
        gen.setStyle(Mixin.STYLE_EVERYTHING);

        Set<Object> elements = new LinkedHashSet<>();
        TypeMap typeMap = new TypeMap<>(new CompleteHierarchyResolver());
        LinkedHashSet<Adapter> localTraits = new LinkedHashSet<>();
        localTraits.addAll(traitable.traits());
        localTraits.addAll(traits);
        localTraits.forEach(typeMap::put);

        TraitStub partial = new TraitStub(traitable, localTraits, typeMap);
        elements.add(partial);
        elements.add(traitable);
        elements.addAll(traits);

        gen.setDelegates(elements.toArray(new Object[elements.size()]));

        return (T) gen.create();
    }

    //@Override
    public <T extends Adaptable> T create2(DynamicTraitable traitable, Set<Adapter> traits) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(traitable.getClass());
        enhancer.setInterfaces(resolveHierarchy(new InterfaceHierarchyResolver(), traitable, traits));
        enhancer.setCallback(new CglibTraitHandler(traitable, traits));

        return (T) enhancer.create();
    }

    private Class[] resolveHierarchy(HierarchyResolver resolver, DynamicTraitable traitable, Set<Adapter> traits) {
        Set<Class<?>> types = Stream.concat(Stream.of(traitable.getClass()), traits.stream().map(Adapter::getClass))
            .flatMap(resolver::resolve)
            .filter(Object.class::equals)
            .collect(Collectors.toSet());

        return types.toArray(new Class[types.size()]);
    }

}
