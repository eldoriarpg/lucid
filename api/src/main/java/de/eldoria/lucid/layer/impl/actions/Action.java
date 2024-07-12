package de.eldoria.lucid.layer.impl.actions;

import de.eldoria.lucid.events.LayerClickEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Action {
    private ItemStack display;

    public Action(ItemStack display) {
        this.display = display;
    }

    public abstract void click(LayerClickEvent event);

    public ItemStack getDisplay() {
        return display;
    }

    public void display(ItemStack itemStack) {
        this.display = itemStack;
    }
}
