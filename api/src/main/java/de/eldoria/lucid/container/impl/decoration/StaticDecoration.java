package de.eldoria.lucid.container.impl.decoration;

import de.eldoria.lucid.container.anchor.Anchor;
import de.eldoria.lucid.container.Container;
import de.eldoria.lucid.container.Position;
import de.eldoria.lucid.scene.Form;
import org.bukkit.inventory.ItemStack;

public class StaticDecoration implements Container {
    private final Form form;
    private final Position position;
    private final Anchor anchor;
    private final ItemStack item;

    public StaticDecoration(Form form, Position position, Anchor anchor, ItemStack item) {
        this.form = form;
        this.position = position;
        this.anchor = anchor;
        this.item = item;
    }

    @Override
    public Form form() {
        return form;
    }

    @Override
    public boolean immutable() {
        return true;
    }

    @Override
    public Anchor anchor() {
        return anchor;
    }

    @Override
    public Position position() {
        return position;
    }

    public ItemStack display(Position position){
        return item;
    }
}
