package org.connectorio.helenus.adaptable.handler.handle;

import org.connectorio.helenus.adaptable.Adapter;
import org.connectorio.helenus.adaptable.handler.DynamicTraitable;
import org.connectorio.helenus.adaptable.handler.resolver.InterfaceHierarchyResolver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JdkTraitHandler extends TraitHandle implements InvocationHandler {

    public JdkTraitHandler(DynamicTraitable traitable, Set<Adapter> traits) {
        super(traitable, traits);
    }

    public static <T> T create(DynamicTraitable traitable, Set<Adapter> traits) {
        InterfaceHierarchyResolver resolver = new InterfaceHierarchyResolver();
        Set<Class<?>> types = Stream.concat(Stream.of(traitable.getClass()), traits.stream().map(Adapter::getClass))
            .flatMap(resolver::resolve)
            .collect(Collectors.toSet());

        Class[] interfaces = types.toArray(new Class[types.size()]);

        Object proxy = Proxy.newProxyInstance(traitable.getClass().getClassLoader(), interfaces,
            new JdkTraitHandler(traitable, traits));

        return (T) proxy;
    }

}
