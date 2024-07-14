package de.eldoria.lucid.layer.anchor.impl;

import de.eldoria.lucid.area.impl.SquaredArea;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.exceptions.ex.InvalidShapeException;
import de.eldoria.lucid.scene.Form;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.eldoria.lucid.layer.anchor.Anchor.CENTER;

class CenterAnchorTest {

    @Test
    void position() {
        Assertions.assertThrows(InvalidShapeException.class, () -> CENTER.position(new Form(10, 10)));
        Form form = new Form(9, 9);
        Assertions.assertEquals(CENTER.position(form), new Position(5, 5));

    }

    @Test
    void area() {
        Assertions.assertThrows(InvalidShapeException.class, () -> CENTER.area(new Form(10, 10), null));

        /*
        0 0 0 0 0 0
        1 x x x x 0
        x x x x x 0
        x x A x x 0
        x x x x x 0
        x x x x 2 0
        0 0 0 0 0 0
         */
        var form = new Form(5, 5);
        SquaredArea area = CENTER.area(form, new Position(2, 3));
        Assertions.assertEquals(new SquaredArea(new Position(0, 1), new Position(4, 5)), area);
    }
}
