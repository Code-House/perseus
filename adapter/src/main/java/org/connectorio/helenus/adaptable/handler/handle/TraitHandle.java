package org.connectorio.helenus.adaptable.handler.handle;

import org.connectorio.helenus.adaptable.Adapter;
import org.connectorio.helenus.adaptable.handler.DynamicTraitable;
import org.connectorio.helenus.adaptable.handler.TraitHandlerFactoryLookup;
import org.connectorio.helenus.adaptable.handler.map.TypeMap;
import org.connectorio.helenus.adaptable.handler.resolver.CompleteHierarchyResolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TraitHandle {

    private final TypeMap<Adapter> typeMap = new TypeMap<>(new CompleteHierarchyResolver());
    private final DynamicTraitable traitable;
    private final Set<Adapter> traits;

    public TraitHandle(DynamicTraitable traitable, Set<Adapter> traits) {
        this.traitable = traitable;
        this.traits = Collections.unmodifiableSet(traits);

        traits.forEach(typeMap::put);
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();

        if (declaringClass == Object.class) {
            if ("equals".equals(method.getName())) {
                return traitable.equals(args[0]);
            }
            if ("hashCode".equals(method.getName())) {
                return traitable.hashCode();
            }
            if ("toString".equals(method.getName())) {
                return traitable.toString();
            }
        }

        if (declaringClass == DynamicTraitable.class) {
            if ("with".equals(method.getName())) {
                if (args.length == 1 && args[0] instanceof Adapter) {
                    LinkedHashSet<Adapter> localTraits = new LinkedHashSet<>(traits);
                    localTraits.add((Adapter) args[0]);
                    return TraitHandlerFactoryLookup.resolve().create(traitable, localTraits);
                }
                throw new IllegalArgumentException(args[0] + " is not valid argument for with() method call");
            }
            if ("adapters".equals(method.getName())) {
                return traits;
            }
            if ("as".equals(method.getName())) {
                if (args.length == 1 && args[0] instanceof Class) {
                    return typeMap.get((Class<?>) args[0])
                        .map(Deque::getFirst);
                }
                throw new IllegalArgumentException(args[0] + " is not valid argument for with() method call");
            }
        }

        if (declaringClass == traitable.getClass() || declaringClass.isAssignableFrom(traitable.getClass())) {
            return proceed(traitable, method, args);
        }

        Optional<? extends Deque<?>> trait = typeMap.get(declaringClass);
        if (trait.isPresent()) {
            return proceed(trait.get().getFirst(), method, args);
        }

        throw new AbstractMethodError();
    }

    private Object proceed(Object instance, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(instance, args);
    }

}
