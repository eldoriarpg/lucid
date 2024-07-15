package de.eldoria.lucid.scene.components;

import org.bukkit.inventory.ItemStack;

public interface InputSink {
    /**
     * Attempts to put an item stack in the scene via this sink.
     * <p>
     * The passed item stack may be added completely or partially.
     *
     * @param item the item stack to add
     * @return null if the stack was added completely or an item stack of type {@link org.bukkit.Material}.
     * Returns what remains in the inventory in case the item stack was added partially.
     * Returns the same item stack again if the item stack was not added
     */
    ItemStack putItem(ItemStack item);
}
