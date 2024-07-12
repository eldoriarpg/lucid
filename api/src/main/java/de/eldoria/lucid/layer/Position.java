package de.eldoria.lucid.layer;

import de.eldoria.lucid.util.Conversion;

public record Position(int x, int y) {
    public static final Position ZERO = new Position(0, 0);
    public static final Position ONE = new Position(1, 1);

    public Position minus(Position position) {
        return new Position(x - position.x, y - position.y);
    }

    public Position minus(int amount) {
        return new Position(x - amount, y - amount);
    }

    public Position plus(Position position) {
        return new Position(x + position.x, y + position.y);
    }

    public Position plus(int amount) {
        return new Position(x + amount, y + amount);
    }

    public static Position fromChestSlot(int slot) {
        return Conversion.chestSlotToPosition(slot);
    }

    public int toChestSlot() {
        return y * 9 + x;
    }
}
