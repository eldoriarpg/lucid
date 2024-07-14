package de.eldoria.lucid.layer.anchor.impl;

import de.eldoria.lucid.area.impl.SquaredArea;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.scene.Form;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.eldoria.lucid.layer.anchor.Anchor.BOTTOM_LEFT;

class BottomLeftAnchorTest {

    @Test
    void position() {
        Form form = new Form(10, 10);
        Assertions.assertEquals(BOTTOM_LEFT.position(form), new Position(0,9));
    }

    @Test
    void area() {
        /*
        0 0 0 0 0 0
        1 x x x x 0
        x x x x x 0
        x x x x x 0
        x x x x x 0
        A x x x 2 0
        0 0 0 0 0 0
         */
        var form = new Form(5,5);
        SquaredArea area = BOTTOM_LEFT.area(form, new Position(0, 5));
        Assertions.assertEquals(new SquaredArea(new Position(0,1), new Position(4,5)), area);
    }
}
