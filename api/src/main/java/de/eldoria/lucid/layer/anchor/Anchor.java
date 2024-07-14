package de.eldoria.lucid.layer.anchor;

import de.eldoria.lucid.area.impl.SquaredArea;
import de.eldoria.lucid.layer.Formed;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.impl.BottomLeftAnchor;
import de.eldoria.lucid.layer.anchor.impl.BottomRightAnchor;
import de.eldoria.lucid.layer.anchor.impl.CenterAnchor;
import de.eldoria.lucid.layer.anchor.impl.TopLeftAnchor;
import de.eldoria.lucid.layer.anchor.impl.TopRightAnchor;

public interface Anchor {
    /**
     * <pre>
     * A x x
     * x x x
     * x x x
     * </pre>
     **/
    Anchor TOP_LEFT = new TopLeftAnchor();
    /**
     * <pre>
     * x x A
     * x x x
     * x x x
     * </pre>
     **/
    Anchor TOP_RIGHT = new TopRightAnchor();
    /**
     * <pre>
     * x x x
     * x x x
     * A x x
     * </pre>
     **/
    Anchor BOTTOM_LEFT = new BottomLeftAnchor();
    /**
     * <pre>
     * x x x
     * x x x
     * x x A
     * </pre>
     **/
    Anchor BOTTOM_RIGHT = new BottomRightAnchor();
    /**
     * <pre>
     * x x x
     * x A x
     * x x x
     * </pre>
     **/
    Anchor CENTER = new CenterAnchor();
    // TODO: Anchors for side alignment
    /**
     * <pre>
     * x x x
     * x x x
     * x A x
     * </pre>
     **/
    Anchor BOTTOM_CENTER = null;
    /**
     * <pre>
     * x A x
     * x x x
     * x x x
     * </pre>
     **/
    Anchor TOP_CENTER = null;
    /**
     * <pre>
     * x x x
     * A x x
     * x x x
     * </pre>
     **/
    Anchor LEFT_CENTER = null;
    /**
     * <pre>
     * x x x
     * x x A
     * x x x
     * </pre>
     **/
    Anchor RIGHT_CENTER = null;

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
     * Returns the {@link SquaredArea} that the inner form is taking up inside the {@link Formed} entity.
     *
     * @param inner    the formed entity
     * @param position the position of {@code inner} inside the current formed entity.
     * @return are that the entity takes up
     */
    SquaredArea area(Formed inner, Position position);
}
