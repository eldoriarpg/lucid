package de.eldoria.lucid.layer.impl.actions;


import de.eldoria.lucid.events.LayerClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;


public class SimpleAction extends Action {
    private final Consumer<LayerClickEvent> consumer;

    public SimpleAction(ItemStack display, Consumer<LayerClickEvent> consumer) {
        super(display);
        this.consumer = consumer;
    }

    @Override
    public void click(LayerClickEvent event) {
        consumer.accept(event);
    }
}
