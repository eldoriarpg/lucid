package de.eldoria.lucid.layer.impl.container;

import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.layer.base.AbstractLayer;
import de.eldoria.lucid.layer.builder.BasicLayerBuilder;
import de.eldoria.lucid.scene.Form;
import de.eldoria.lucid.scene.components.InputSink;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Material.AIR;
import static org.bukkit.event.inventory.ClickType.LEFT;
import static org.bukkit.event.inventory.ClickType.RIGHT;
import static org.bukkit.event.inventory.ClickType.SHIFT_LEFT;
import static org.bukkit.event.inventory.ClickType.SHIFT_RIGHT;

public class ItemStackContainerLayer extends AbstractLayer implements InputSink {
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
        // TODO: Shift clicks and right clicks are very buggy still
        ItemStack cursor = event.event().getCursor();
        Position position = event.position();
        ItemStack current = stacks.get(position);
        ClickType click = event.event().getClick();
        if (cursor.getType() == AIR) {
            if (click == RIGHT && current != null) {
                current.setAmount(Math.floorDiv(current.getAmount(), 2));
            } else {
                stacks.remove(position);
            }
        } else {
            if (current == null) { // No item in the current slot
                ItemStack clone = cursor.clone();
                if (click == RIGHT) {
                    clone.setAmount(1);
                    stacks.put(position, clone);
                } else if (click == LEFT) {
                    stacks.put(position, clone);
                } else {
                    return;
                }
            } else if (current.isSimilar(cursor)) { // The item in the slot is similar
                if (click == RIGHT) {
                    if (current.getMaxStackSize() != current.getAmount()) {
                        current.setAmount(current.getAmount() + 1);
                    }
                } else {
                    current.setAmount(Math.min(current.getMaxStackSize(), cursor.getAmount() + current.getAmount()));
                }
            } else { // The slot is not similar
                if (click == RIGHT || click == LEFT) {
                    stacks.put(position, cursor.clone());
                } else if (click == SHIFT_LEFT || click == SHIFT_RIGHT) {
                    stacks.remove(position);
                }
            }
        }

        redraw();
    }

    @Override
    public ItemStack putItem(ItemStack item) {
        Position similarSlot = findSimilarSlot(item);
        while (similarSlot != null) {
            ItemStack similarStack = stacks.get(similarSlot);
            int amount = item.getAmount();
            int maxAdd = similarStack.getMaxStackSize() - similarStack.getAmount();
            similarStack.setAmount(similarStack.getAmount() + Math.min(amount, maxAdd));
            amount = amount - Math.min(maxAdd, amount);
            if (amount == 0) {
                redraw();
                return null; // Item Stack exhausted
            }
            item.setAmount(amount); // Update to add with the remaining amount
            similarSlot = findSimilarSlot(item);

        }
        Position freeSlot = findFreeSlot();
        if (freeSlot != null) {
            stacks.put(freeSlot, item);
            redraw();
            return null;
        }
        return item;
    }

    /**
     * Returns the first free slot where no item is located
     *
     * @return the position of the free slot or null if no slot was found
     */
    private Position findFreeSlot() {
        for (int y = 0; y < form().vertical(); y++) {
            for (int x = 0; x < form().horizontal(); x++) {
                var current = new Position(x, y);
                if (stacks.containsKey(current)) continue;
                return current;
            }
        }
        return null;
    }

    /**
     * Returns the first found slot that contains a similar item stack that hasn't reached max stack size
     *
     * @param stack stack to check against
     * @return the position of the slot or null if no slot was found
     */
    @Nullable
    private Position findSimilarSlot(ItemStack stack) {
        for (int y = 0; y < form().vertical(); y++) {
            for (int x = 0; x < form().horizontal(); x++) {
                var current = new Position(x, y);
                if (!stacks.containsKey(current)) continue;
                ItemStack currentStack = stacks.get(current);
                if (!currentStack.isSimilar(stack)) continue;
                if (currentStack.getAmount() == currentStack.getMaxStackSize()) continue;
                return current;
            }
        }
        return null;
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
