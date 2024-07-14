package de.eldoria.lucid.events;

import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.scene.Scene;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Set;

public interface LayerClickEvent {
    void redraw(Layer layer);

    void redrawAll();

    Set<Layer> redraw();

    boolean isRedrawAll();

    Player player();

    Scene scene();

    InventoryClickEvent event();

    Position position();
}
