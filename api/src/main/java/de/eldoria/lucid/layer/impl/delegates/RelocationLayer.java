package de.eldoria.lucid.layer.impl.delegates;

import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import org.bukkit.inventory.ItemStack;

/**
 * A layer that is wrapping another layer and changing its position.
 * <p>
 * This can be used when a layer should be in two scenes but on different positions.
 */
public class RelocationLayer extends DelegatingLayer {
    private final Position position;

    RelocationLayer(Layer layer, Position position) {
        super(layer);
        this.position = position;
    }

    public static RelocationLayer wrap(Layer layer, Position newPosition){
        return new RelocationLayer(layer, newPosition);
    }

    @Override
    public Position position() {
        return position;
    }
}
