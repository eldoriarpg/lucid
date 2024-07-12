package de.eldoria.lucid.layer.anchor.impl;

import de.eldoria.lucid.layer.Area;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.scene.Form;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.eldoria.lucid.layer.anchor.Anchor.TOP_LEFT;

class TopLeftAnchorTest {

    @Test
    void position() {
        Form form = new Form(10, 10);
        Assertions.assertEquals(TOP_LEFT.position(form), new Position(0, 0));
    }

    @Test
    void area() {
        /*
        0 0 0 0 0 0
        A x x x x 0
        x x x x x 0
        x x x x x 0
        x x x x x 0
        x x x x 2 0
        0 0 0 0 0 0
         */
        var form = new Form(5, 5);
        Area area = TOP_LEFT.area(form, new Position(0, 1));
        Assertions.assertEquals(new Area(new Position(0, 1), new Position(4, 5)), area);
    }
}
