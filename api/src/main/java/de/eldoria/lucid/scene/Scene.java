package de.eldoria.lucid.scene;

import de.eldoria.lucid.builder.Buildable;
import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.exceptions.Checks;
import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.util.Conversion;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.LinkedList;
import java.util.List;

/**
 * A scene that represents the view of a player.
 * <p>
 * One scene is equal to one gui, which may change during time or transition to another scene.
 */
public class Scene {
    private final LayerCatalog catalog;
    private final String title;
    private Inventory inventory;

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

    public void transition(Scene scene) {
        // TODO:
    }

    public void close(Player player) {
        //TODO
    }

    private void calculate() {
        catalog.calculate(this);
    }

    public void apply(Inventory inventory) {
        if (this.inventory == null) this.inventory = inventory;
        for (int slot = 0; slot < catalog.topLayer().size(); slot++) {
            refreshPosition(slot);
        }
    }

    private void refreshPosition(int slot) {
        var position = Conversion.chestSlotToPosition(slot);
        Layer layer = catalog.layerAtPosition(position);
        inventory.setItem(slot, layer.getDisplay(catalog.toLayerPosition(layer, position)));
    }

    public void click(Player player, InventoryClickEvent event) {
        Position position = Conversion.chestSlotToPosition(event.getSlot());
        Layer layer = catalog.layerAtPosition(position);
        LayerClickEvent clickEvent = new LayerClickEvent(player, this, event, catalog.toLayerPosition(layer, position));
        layer.click(clickEvent);
        if (clickEvent.isRedrawAll()) {
            for (Scene scene : layer.registry().scenes()) {
                scene.redrawAll();
            }
        } else {
            for (Layer redraw : clickEvent.redraw()) {
                for (Scene scene : redraw.registry().scenes()) {
                    scene.redraw(redraw);
                }
            }
        }
    }

    private void redrawAll() {
        for (Layer layer : catalog.layers()) {
            redraw(layer);
        }
    }

    private void redraw(Layer layer) {
        for (Position position : catalog.layerPositions(layer)) {
            inventory.setItem(position.toChestSlot(), layer.getDisplay(catalog.toLayerPosition(layer, position)));
        }
    }

    public String title() {
        return title;
    }

    public int size() {
        return catalog.size();
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
