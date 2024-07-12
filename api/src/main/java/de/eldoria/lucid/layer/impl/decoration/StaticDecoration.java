package de.eldoria.lucid.layer.impl.decoration;

import de.eldoria.lucid.layer.ImmutableLayer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.layer.builder.BasicLayerBuilder;
import de.eldoria.lucid.scene.Form;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StaticDecoration implements ImmutableLayer {
    private final Form form;
    private final Position position;
    private final Anchor anchor;
    private final int priority;
    private final ItemStack item;

    protected StaticDecoration(Form form, Position position, Anchor anchor, int priority, ItemStack item) {
        this.form = form;
        this.position = position;
        this.anchor = anchor;
        this.priority = priority;
        this.item = item;
    }

    public static Builder builder(Form form, ItemStack itemStack) {
        return new Builder(form, itemStack);
    }
    public static Builder builder(Form form, Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("");
        itemStack.setItemMeta(meta);
        return builder(form, itemStack);
    }

    @Override
    public Form form() {
        return form;
    }


    @Override
    public Anchor anchor() {
        return anchor;
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public int priority() {
        return priority;
    }

    public ItemStack display(Position position) {
        return item;
    }

    public static class Builder extends BasicLayerBuilder<Builder, StaticDecoration> {
        private final ItemStack item;

        protected Builder(Form form, ItemStack item) {
            super(form);
            this.item = item;
        }

        @Override
        public StaticDecoration build() {
            return new StaticDecoration(form, position, anchor, priority, item);
        }
    }
}
