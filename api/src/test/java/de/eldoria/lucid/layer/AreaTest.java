package de.eldoria.lucid.layer;

import de.eldoria.lucid.layer.impl.misc.TopLayer;
import de.eldoria.lucid.scene.Form;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AreaTest {

    @Test
    void iterator() {
        Area area = new Area(new Position(0, 0), new Position(2, 2));
        List<Position> positions = new ArrayList<>();
        for (Position position : area) {
            positions.add(position);
        }
        Assertions.assertEquals(3 * 3, positions.size());
        var expected = List.of(
                new Position(0, 0),
                new Position(0, 1),
                new Position(0, 2),
                new Position(1, 0),
                new Position(1, 1),
                new Position(1, 2),
                new Position(2, 0),
                new Position(2, 1),
                new Position(2, 2)
        );
        Assertions.assertEquals(expected, positions);
    }

    @Test
    void iterator2() {
    }
}
