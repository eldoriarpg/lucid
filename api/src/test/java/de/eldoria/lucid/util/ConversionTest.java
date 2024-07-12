package de.eldoria.lucid.util;

import de.eldoria.lucid.layer.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConversionTest {

    @Test
    void chestSlotToPosition() {
        Assertions.assertEquals(Conversion.chestSlotToPosition(0), new Position(0,0));
        Assertions.assertEquals(Conversion.chestSlotToPosition(9), new Position(0,1));
        Assertions.assertEquals(Conversion.chestSlotToPosition(22), new Position(4,2));
        Assertions.assertEquals(Conversion.chestSlotToPosition(31), new Position(4,3));
        Assertions.assertEquals(Conversion.chestSlotToPosition(53), new Position(8,5));
    }
}
