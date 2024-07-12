package de.eldoria.lucid.layer.anchor.impl;

import de.eldoria.lucid.layer.Area;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.scene.Form;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.eldoria.lucid.layer.anchor.Anchor.TOP_RIGHT;

class TopRightAnchorTest {

    @Test
    void position() {
        Form form = new Form(10, 10);
        Assertions.assertEquals(TOP_RIGHT.position(form), new Position(9, 0));

    }

    @Test
    void area() {
        /*
        0 1 x x x A 0
        0 x x x x x 0
        0 x x x x x 0
        0 x x x x x 0
        0 x x x x 2 0
        0 0 0 0 0 0 0
         */
        var form = new Form(5, 5);
        Area area = TOP_RIGHT.area(form, new Position(5, 0));
        Assertions.assertEquals(new Area(new Position(1, 0), new Position(5, 4)), area);

    }
}
