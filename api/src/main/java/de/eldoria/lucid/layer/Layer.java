package de.eldoria.lucid.layer;

import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.layer.impl.misc.NullLayer;
import de.eldoria.lucid.scene.Scene;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface Layer extends FormHolder {
    /**
     * A dummy layer that simply returns nothing.
     */
    Layer EMPTY = new NullLayer();

    /**
     * The anchor position defining where in the layer the {@link #position()} is located.
     * The {@link #form()} will be placed based on that position
     *
     * @return the anchor position
     */
    Anchor anchor();

    /**
     * The position where the {@link #anchor()} of the layer is located.
     *
     * @return position of the anchor
     */
    Position position();

    /**
     * Gets the area in which the inner layer is located inside this layer
     *
     * @param inner the layer which is located in this layer
     * @return an area inside this layer in local coordinated of this layer
     */
    default Area area(Layer inner) {
        return anchor().area(inner, inner.position());
    }

    int priority();

    /**
     * The item stack to be displayed at the local position inside the layer.
     *
     * @param position the position that is requested. The position is the local position inside the layer and might <b>not</b> be the inventory position.
     * @return item stack instance. That instance might be a clone of an existing instance.
     */
    ItemStack display(Position position);

    /**
     * Converts a local position inside the outer form into a position of this layer
     *
     * @param outerForm form of the passed local position
     * @param position  the local position inside the outer form
     * @return new position inside this layer.
     */
    default Position toLayerPosition(Formed outerForm, Position position) {
        return null;
    }

    void click(Scene scene, Player player, InventoryClickEvent event);
}