package de.eldoria.lucid.container.anchor;

import de.eldoria.lucid.container.Area;
import de.eldoria.lucid.container.Formed;
import de.eldoria.lucid.container.Position;
import de.eldoria.lucid.container.anchor.impl.BottomLeftAnchor;
import de.eldoria.lucid.container.anchor.impl.BottomRightAnchor;
import de.eldoria.lucid.container.anchor.impl.CenterAnchor;
import de.eldoria.lucid.container.anchor.impl.TopLeftAnchor;
import de.eldoria.lucid.container.anchor.impl.TopRightAnchor;

public interface Anchor {
    Anchor TOP_LEFT = new TopLeftAnchor();
    Anchor TOP_RIGHT = new TopRightAnchor();
    Anchor BOTTOM_LEFT = new BottomLeftAnchor();
    Anchor BOTTOM_RIGHT = new BottomRightAnchor();
    Anchor CENTER = new CenterAnchor();

    default String name() {
        return getClass().getSimpleName();
    }

    /**
     * Returns the local position of the anchor inside the form
     *
     * @param form the form to get the local position in
     * @return local position inside the form
     */
    Position position(Formed form);

    /**
     * Returns the {@link Area} that the inner form is taking up inside the {@link Formed} entity.
     *
     * @param inner    the formed entity
     * @param position the position of {@code inner} inside the current formed entity.
     * @return are that the entity takes up
     */
    Area area(Formed inner, Position position);
}
