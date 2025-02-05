package de.eldoria.lucid.layer.anchor.impl;

import de.eldoria.lucid.area.impl.SquaredArea;
import de.eldoria.lucid.layer.Formed;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;

public class TopRightAnchor implements Anchor {

    @Override
    public Position position(Formed form) {
        return new Position(form.horizontal() - 1, 0);
    }

    @Override
    public SquaredArea area(Formed inner, Position position) {
        return new SquaredArea(
                new Position(position.x() - inner.horizontal() + 1, position.y()),
                new Position(position.x(), position.y() + inner.vertical() -1)
        );
    }
}
