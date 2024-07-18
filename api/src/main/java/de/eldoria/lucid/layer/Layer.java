package de.eldoria.lucid.layer;

import de.eldoria.lucid.area.impl.SquaredArea;
import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.layer.impl.delegates.ImmutabilityLayer;
import de.eldoria.lucid.layer.impl.delegates.RelocationLayer;
import de.eldoria.lucid.layer.impl.misc.NullLayer;
import de.eldoria.lucid.scene.Scene;
import de.eldoria.lucid.scene.SceneRegistry;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface Layer extends FormHolder {
    /**
     * A dummy layer that simply returns nothing.
     */
    Layer EMPTY = new NullLayer();

    /**
     * The unique ID of this layer.
     * This value should be the only value used in {@link #equals(Object)} and {@link #hashCode()}
     *
     * @return uid for this layer
     */
    UUID uid();

    /**
     * A registry where scenes in which this layer is contained can register.
     *
     * @return the scene registry
     */
    SceneRegistry registry();

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
    default SquaredArea area(Layer inner) {
        return anchor().area(inner, inner.position());
    }

    /**
     * The priority of the layer.
     * If layers with different priorities are overlapping, the layer with higher priority takes precedence.
     * If two layers of the same priorities are overlapping the layer that was added last takes precedence.
     *
     * @return priority
     */
    int priority();

    /**
     * The item stack to be displayed at the local position inside the layer.
     *
     * @param position the position that is requested. The position is the local position inside the layer and might <b>not</b> be the inventory position.
     * @return item stack instance. That instance might be a clone of an existing instance.
     */
    ItemStack displayAt(Position position);

    /**
     * Converts a local position inside the outer form into a position of this layer
     *
     * @param inner    form of the passed local position
     * @param position the local position inside the outer form
     * @return new position inside this layer.
     */
    default Position toLayerPosition(Layer inner, Position position) {
        SquaredArea area = this.area(inner);
        return position.minus(area.min());
    }

    /**
     * Called when a click on this layer happened
     *
     * @param event the click event
     */
    void click(LayerClickEvent event);

    /**
     * Requests the scenes that hold this layer to redraw fields where this layer is visible.
     * <p>
     * The redraw will happen in the next server tick.
     */
    default void redraw() {
        for (Scene scene : registry().scenes()) {
            scene.redrawLater(this);
        }
    }

    default Layer relocate(Position position) {
        return RelocationLayer.wrap(this, position);
    }

    default Layer makeImmutable() {
        return ImmutabilityLayer.wrap(this);
    }

    /**
     * Called on every server tick
     */
    void tick();
}
