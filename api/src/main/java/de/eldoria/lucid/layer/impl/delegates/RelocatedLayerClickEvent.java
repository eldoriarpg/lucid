package de.eldoria.lucid.layer.impl.delegates;

import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.scene.Scene;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Set;

public class RelocatedLayerClickEvent implements LayerClickEvent {
    private final Position position;
    public LayerClickEvent delegate;

    public RelocatedLayerClickEvent(LayerClickEvent layerClickEvent, Position position) {
        this.delegate = layerClickEvent;
        this.position = position;
    }

    public static RelocatedLayerClickEvent fromOffset(LayerClickEvent event, Position offset) {
        return new RelocatedLayerClickEvent(event, event.position().plus(offset));
    }

    @Override
    public void redraw(Layer layer) {
        delegate.redraw(layer);
    }

    @Override
    public void redrawAll() {
        delegate.redrawAll();
    }

    @Override
    public Set<Layer> redraw() {
        return delegate.redraw();
    }

    @Override
    public boolean isRedrawAll() {
        return delegate.isRedrawAll();
    }

    @Override
    public Player player() {
        return delegate.player();
    }

    @Override
    public Scene scene() {
        return delegate.scene();
    }

    @Override
    public InventoryClickEvent event() {
        return delegate.event();
    }

    @Override
    public Position position() {
        return position;
    }
}
