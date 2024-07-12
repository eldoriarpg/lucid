package de.eldoria.lucid.container.anchor.impl;

import de.eldoria.lucid.container.Area;
import de.eldoria.lucid.container.Formed;
import de.eldoria.lucid.container.Position;
import de.eldoria.lucid.container.anchor.Anchor;

public class BottomRightAnchor implements Anchor {

    @Override
    public Position position(Formed form) {
        return new Position(form.horizontal() - 1, form.vertical() - 1);
    }

    @Override
    public Area area(Formed inner, Position position) {
        return new Area(
                new Position(position.x() - inner.horizontal() + 1, position.y() - inner.vertical() + 1),
                new Position(position.x(), position.y())
        );
    }
}
