package org.connectorio.helenus.adaptable.struct;

import org.connectorio.helenus.adaptable.StaticAdaptable;

public class StaticWardrobe extends StaticAdaptable implements Wardrobe {

    @Override
    public String describe() {
        return "Static Wardrobe";
    }

}
