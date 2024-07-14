package de.eldoria.lucid.layer.impl.container;

import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.layer.base.AbstractLayer;
import de.eldoria.lucid.layer.builder.BasicLayerBuilder;
import de.eldoria.lucid.scene.Form;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.AIR;

public class ItemStackContainerLayer extends AbstractLayer {
    private final Map<Position, ItemStack> stacks = new HashMap<>();

    ItemStackContainerLayer(Form form, Position position, Anchor anchor, int priority) {
        super(form, position, anchor, priority);
    }

    public static Builder builder(Form form) {
        return new Builder(form);
    }

    @Override
    public ItemStack displayAt(Position position) {
        return stacks.get(position);
    }

    @Override
    public void click(LayerClickEvent event) {
        ItemStack cursor = event.event().getCursor();
        if (cursor.getType() == AIR) {
            stacks.remove(event.position());
        } else {
            stacks.put(event.position(), cursor.clone());
        }
        redraw();
    }

    public static class Builder extends BasicLayerBuilder<Builder, ItemStackContainerLayer> {
        public Builder(Form form) {
            super(form);
        }

        @Override
        public ItemStackContainerLayer build() {
            return new ItemStackContainerLayer(form, position, anchor, priority);
        }
    }
}
