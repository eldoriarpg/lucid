package de.eldoria.lucid.scene;

import de.eldoria.lucid.builder.Buildable;
import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.events.LayerClickEventImpl;
import de.eldoria.lucid.exceptions.Checks;
import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.util.Conversion;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * A scene that represents the view of a player.
 * <p>
 * One scene is equal to one gui, which may change during time or transition to another scene.
 */
public class Scene {
    private final LayerCatalog catalog;
    private final String title;
    private Inventory inventory;
    private final Set<UUID> redraw = new HashSet<>();

    private Scene(LayerCatalog catalog, String title) {
        this.catalog = catalog;
        this.title = title;
    }

    public static Builder builder(int lines) {
        return Builder.create(lines);
    }

    public static Builder builder(Form lines) {
        return Builder.create(lines);
    }

    public void close(Player player) {
        //TODO
    }

    private void calculate() {
        catalog.calculate(this);
    }

    /**
     * Applies this scene to an inventory and binds this inventory onto a scene.
     * This should be only called once on a scene.
     *
     * @param inventory the inventory that this scene will be tied to.
     */
    public void apply(Inventory inventory) {
        if (this.inventory == null) this.inventory = inventory;
        for (int slot = 0; slot < catalog.topLayer().size(); slot++) {
            refreshPosition(slot);
        }
    }

    private void refreshPosition(int slot) {
        var position = Conversion.chestSlotToPosition(slot);
        catalog.layerAtPosition(position)
                .ifPresent(layer -> inventory.setItem(slot, layer.displayAt(catalog.toLayerPosition(layer, position))));
    }

    public void click(Player player, InventoryClickEvent event) {
        Position position = Conversion.chestSlotToPosition(event.getSlot());
        Optional<Layer> layer = catalog.layerAtPosition(position);
        if (layer.isEmpty()) return;
        LayerClickEvent clickEvent = new LayerClickEventImpl(player, this, event, catalog.toLayerPosition(layer.orElse(null), position));
        layer.get().click(clickEvent);
        if (clickEvent.isRedrawAll()) {
            for (Scene scene : layer.get().registry().scenes()) {
                scene.redrawAll();
            }
        } else {
            for (Layer redraw : clickEvent.redraw()) {
                for (Scene scene : redraw.registry().scenes()) {
                    scene.redrawLater(redraw);
                }
            }
        }
    }

    /**
     * Redraws all layers that are visible in this scene.
     */
    public void redrawAll() {
        for (Layer layer : catalog.layers()) {
            redrawLater(layer);
        }
    }

    /**
     * Redraws this layer, where it is visible in this scene
     *
     * @param layer redraws this layer inside this scene.
     */
    public void redrawLater(UUID layer) {
        redraw.add(layer);
    }

    /**
     * Redraws this layer, where it is visible in this scene
     *
     * @param layer redraws this layer inside this scene.
     */
    public void redrawLater(Layer layer) {
        redrawLater(layer.uid());
    }

    public void redraw(UUID uid) {
        Layer layer = catalog.byUID(uid);
        for (Position position : catalog.layerPositions(uid)) {
            inventory.setItem(position.toChestSlot(), layer.displayAt(catalog.toLayerPosition(layer, position)));
        }
    }

    public String title() {
        return title;
    }

    public int size() {
        return catalog.size();
    }

    public void tick() {
        for (UUID layer : redraw) {
            redraw(layer);
        }
        redraw.clear();
        // Listeners are running first and after that the schedulers.
        // This will delay every redraw by one tick essentially.
    }

    public static class Builder {
        private final Form form;
        private final List<Layer> layerList = new LinkedList<>();
        private String title;

        private Builder(Form form) {
            this.form = form;
        }

        public static Builder create(int lines) {
            return create(new Form(9, lines));
        }

        public static Builder create(Form form) {
            Checks.assertSceneForm(form);
            return new Builder(form);
        }

        public <T extends Layer> Builder add(Buildable<T> layer) {
            return add(layer.build());
        }

        public Builder add(Layer layer) {
            layerList.add(layer);
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Scene build() {
            Scene scene = new Scene(new LayerCatalog(form, layerList), title);
            scene.calculate();
            return scene;
        }
    }
}
