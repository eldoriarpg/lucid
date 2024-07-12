package de.eldoria.lucid.container.anchor.impl;

import de.eldoria.lucid.container.Area;
import de.eldoria.lucid.container.Formed;
import de.eldoria.lucid.container.Position;
import de.eldoria.lucid.container.anchor.Anchor;

public class TopLeftAnchor implements Anchor {

    @Override
    public Position position(Formed form) {
        return new Position(0, 0);
    }

    @Override
    public Area area(Formed inner, Position position) {
        return new Area(
                new Position(position.x(), position.y()),
                new Position(position.x() + inner.horizontal() - 1, position.y() + inner.vertical() - 1)
        );
    }
}
