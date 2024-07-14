package de.eldoria.lucid.layer.impl.delegates;

import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.layer.Layer;

public class ImmutabilityLayer extends DelegatingLayer {
    public ImmutabilityLayer(Layer layer) {
        super(layer);
    }

    public static ImmutabilityLayer wrap(Layer layer) {
        return new ImmutabilityLayer(layer);
    }

    @Override
    public void click(LayerClickEvent event) {
        event.event().setCancelled(true);
    }
}
