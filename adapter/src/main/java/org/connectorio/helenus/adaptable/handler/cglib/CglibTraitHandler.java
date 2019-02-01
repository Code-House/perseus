package org.connectorio.helenus.adaptable.handler.cglib;

import net.sf.cglib.proxy.InvocationHandler;
import org.connectorio.helenus.adaptable.Adapter;
import org.connectorio.helenus.adaptable.handler.DynamicTraitable;
import org.connectorio.helenus.adaptable.handler.handle.TraitHandle;

import java.util.Set;

public class CglibTraitHandler extends TraitHandle implements InvocationHandler {

    public CglibTraitHandler(DynamicTraitable traitable, Set<Adapter> traits) {
        super(traitable, traits);
    }

}
