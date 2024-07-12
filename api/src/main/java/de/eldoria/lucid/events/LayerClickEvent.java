package de.eldoria.lucid.events;

import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.scene.Scene;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class LayerClickEvent {
    private final Player player;
    private final Scene scene;
    private final InventoryClickEvent event;
    private final Position position;
    private final Set<Layer> redraw = new HashSet<>();
    private boolean redrawAll = false;

    public LayerClickEvent(Player player, Scene scene, InventoryClickEvent event, Position position) {
        this.player = player;
        this.scene = scene;
        this.event = event;
        this.position = position;
    }

    public void redraw(Layer layer) {
        redraw.add(layer);
    }

    public void redrawAll() {
        redrawAll = true;
    }

    public Set<Layer> redraw() {
        return Collections.unmodifiableSet(redraw);
    }

    public boolean isRedrawAll() {
        return redrawAll;
    }

    public Player player() {
        return player;
    }

    public Scene scene() {
        return scene;
    }

    public InventoryClickEvent event() {
        return event;
    }

    public Position position() {
        return position;
    }
}
