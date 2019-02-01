package org.connectorio.helenus.adaptable;

import org.connectorio.helenos.trait.struct.*;
import org.connectorio.helenus.adaptable.struct.Door;
import org.connectorio.helenus.adaptable.struct.FurnitureElement;
import org.connectorio.helenus.adaptable.struct.RootFurniture;
import org.connectorio.helenus.adaptable.struct.Shelf;
import org.junit.jupiter.api.Test;

import java.util.Optional;

abstract class AbstractTraitTest {

    private final Adaptable type;

    AbstractTraitTest(Adaptable type) {
        this.type = type;
    }

    @Test
    public void withShelf() {
        RootFurniture wardrobe = type.with(new Shelf());

//        Assertions.assertThat(wardrobe)
//            .isInstanceOf(RootFurniture.class)
//            .isInstanceOf(FurnitureElement.class);

        System.out.println("to string " + wardrobe);
        System.out.println("describe " + wardrobe.describe());

        FurnitureElement element = type.as(Shelf.class).get();
        System.out.println("to string " + element);
        System.out.println("describe " + element.describeElement());
    }

    @Test
    public void withShelfAndDoors() {
        RootFurniture wardrobe = type.with(new Shelf()).with(new Door());

        System.out.println("to string " + wardrobe);
        System.out.println("describe " + wardrobe.describe());

        FurnitureElement element = type.as(Shelf.class).get();
        System.out.println("to string " + element);
        System.out.println("describe " + element.describeElement());

        Optional<Door> maybeDoor = type.as(Door.class);
        Door door = maybeDoor.get();
        System.out.println("to string " + door);
        System.out.println("describe " + door.describeElement());
    }
}