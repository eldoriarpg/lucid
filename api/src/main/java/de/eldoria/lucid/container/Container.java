package de.eldoria.lucid.container;

import de.eldoria.lucid.container.anchor.Anchor;
import org.bukkit.inventory.ItemStack;

public interface Container extends FormHolder {
    /**
     * Whether content of this container is immutable and can not be modified by the player.
     *
     * @return true when immutable
     */
    boolean immutable();

    /**
     * The anchor position defining where in the container the {@link #position()} is located.
     * The {@link #form()} will be placed based on that position
     *
     * @return the anchor position
     */
    Anchor anchor();

    /**
     * The position where the {@link #anchor()} of the container is located.
     *
     * @return position of the anchor
     */
    Position position();

    /**
     * Gets the area in which this container is located inside the form
     * @param formed the form in which this container is located
     * @return an area inside the form
     */
    default Area area(Formed formed) {
        return anchor().area(formed, position());
    }

    /**
     * The item stack to be displayed at the local position inside the container.
     *
     * @param position the position that is requested. The position is the local position inside the container and might <b>not</b> be the inventory position.
     * @return item stack instance. That instance might be a clone of an existing instance.
     */
    ItemStack display(Position position);

    /**
     * Converts a local position inside the outer form into a position of this container
     *
     * @param outerForm form of the passed local position
     * @param position  the local position inside the outer form
     * @return new position inside this container.
     */
    default Position toContainerPosition(Formed outerForm, Position position) {
        return null;
    }
}
