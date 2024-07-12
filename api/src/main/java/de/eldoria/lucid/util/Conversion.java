package de.eldoria.lucid.util;

import de.eldoria.lucid.container.Container;
import de.eldoria.lucid.container.Position;
import net.kyori.adventure.text.BlockNBTComponent;

public final class Conversion {
    public static Position chestSlotToPosition(int slot) {
        int y = slot / 9;
        int x = slot % 9;
        return new Position(x, y);
    }
}
