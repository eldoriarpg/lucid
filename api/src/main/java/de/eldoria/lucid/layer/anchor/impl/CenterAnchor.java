package de.eldoria.lucid.layer.anchor.impl;

import de.eldoria.lucid.layer.Area;
import de.eldoria.lucid.layer.Formed;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.exceptions.Checks;

public class CenterAnchor implements Anchor {
    @Override
    public Position position(Formed form) {
        Checks.assertNotEvenForm(form);
        return new Position(form.horizontal() / 2 + 1, form.vertical() / 2 + 1);
    }

    @Override
    public Area area(Formed inner, Position position) {
        Checks.assertNotEvenForm(inner);
        var hHalf = inner.horizontal() / 2;
        var vHalf = inner.vertical() / 2;
        return new Area(
                new Position(position.x() - hHalf, position.y() - vHalf),
                new Position(position.x() + hHalf, position.y() + vHalf)
        );
    }
}
