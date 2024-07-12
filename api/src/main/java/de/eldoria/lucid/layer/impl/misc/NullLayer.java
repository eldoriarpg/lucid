package de.eldoria.lucid.layer.impl.misc;

import de.eldoria.lucid.layer.ImmutableLayer;
import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.scene.Form;
import org.bukkit.inventory.ItemStack;

public class NullLayer implements ImmutableLayer {
    @Override
    public boolean immutable() {
        return false;
    }

    @Override
    public Anchor anchor() {
        return null;
    }

    @Override
    public Position position() {
        return null;
    }

    @Override
    public int priority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public ItemStack display(Position position) {
        return null;
    }

    @Override
    public Form form() {
        return null;
    }
}
