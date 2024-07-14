package de.eldoria.lucid.events;

import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.scene.Scene;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LayerClickEventImpl implements LayerClickEvent {
    private final Player player;
    private final Scene scene;
    private final InventoryClickEvent event;
    private final Position position;
    private final Set<Layer> redraw = new HashSet<>();
    private boolean redrawAll = false;

    public LayerClickEventImpl(Player player, Scene scene, InventoryClickEvent event, Position position) {
        this.player = player;
        this.scene = scene;
        this.event = event;
        this.position = position;
    }

    /**
     * Request a layer to be redrawn in all scenes after the invite was handled.
     *
     * @param layer the layer to be redrawn
     */
    @Override
    public void redraw(Layer layer) {
        redraw.add(layer);
    }

    /**
     * Requests all scenes that are holding the layer of the current event to redraw all layers
     */
    @Override
    public void redrawAll() {
        redrawAll = true;
    }

    /**
     * An unmodifiable set of layers that should be redrawn.
     *
     * @return unmodifiable set
     */
    @Override
    public Set<Layer> redraw() {
        return Collections.unmodifiableSet(redraw);
    }

    /**
     * True if {@link #redrawAll()} was called and all layers in all connected scenes should be redrawn.
     *
     * @return true if a redraw is requested
     */
    @Override
    public boolean isRedrawAll() {
        return redrawAll;
    }

    /**
     * The player that triggered this event.
     *
     * @return a player instance
     */
    @Override
    public Player player() {
        return player;
    }

    /**
     * The scene in which this even took player
     *
     * @return scene
     */
    @Override
    public Scene scene() {
        return scene;
    }

    /**
     * The raw inventory click event
     *
     * @return inventory click event
     */
    @Override
    public InventoryClickEvent event() {
        return event;
    }

    /**
     * The field that was clicked inside this layer as local position.
     *
     * @return local position
     */
    @Override
    public Position position() {
        return position;
    }
}
