package de.eldoria.lucid.scene;

import de.eldoria.lucid.builder.Buildable;
import de.eldoria.lucid.exceptions.Checks;
import de.eldoria.lucid.layer.FormHolder;
import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.impl.misc.TopLayer;
import de.eldoria.lucid.util.Conversion;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * A scene that represents the view of a player.
 * <p>
 * One scene is equal to one gui, which may change during time or transition to another scene.
 */
public class Scene implements FormHolder {
    private final Layer topLayer;
    private final Map<Position, Layer> layers;
    private final String title;

    private Scene(Layer layer, Map<Position, Layer> layers, String title) {
        this.topLayer = layer;
        this.layers = layers;
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

    public void apply(Inventory inventory) {
        for (int slot = 0; slot < topLayer.size(); slot++) {
            Position position = Conversion.chestSlotToPosition(slot);
            inventory.setItem(slot, layers.getOrDefault(position, Layer.EMPTY).display(position));
        }
    }

    @Override
    public Form form() {
        return topLayer.form();
    }

    public void click(InventoryClickEvent event) {
        Position position = Conversion.chestSlotToPosition(event.getSlot());
        layers.get(position).click(this, (Player) event.getWhoClicked(), event);
    }

    public String title() {
        return title;
    }

    public static class Builder {
        private final Layer form;
        private final Map<Position, Layer> layers = new HashMap<>();
        private String title;

        private Builder(Form form) {
            this.form = new TopLayer(form);
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
            for (Position position : form.area(layer)) {
                layers.compute(position, (p, l) -> {
                    if (l == null) return layer;
                    // If the layers have the same priority, the later one takes precedence as if they were stacked.
                    if (l.priority() == layer.priority()) return layer;
                    return l.priority() < layer.priority() ? l : layer;
                });
            }
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Scene build() {
            return new Scene(form, layers, title);
        }
    }
}
