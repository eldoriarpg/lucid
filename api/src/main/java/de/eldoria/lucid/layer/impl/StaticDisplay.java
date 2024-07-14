package de.eldoria.lucid.layer.impl;

import de.eldoria.lucid.layer.Layer;
import org.bukkit.inventory.ItemStack;

public interface StaticDisplay extends Layer {
    /**
     * Changes the display item of this layer.
     * @param item item
     */
    void updateDisplay(ItemStack item);

}
