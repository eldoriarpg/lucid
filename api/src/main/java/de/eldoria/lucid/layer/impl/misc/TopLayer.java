package de.eldoria.lucid.layer.impl.misc;

import de.eldoria.lucid.layer.ImmutableLayer;
import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.scene.Form;
import org.bukkit.inventory.ItemStack;

public class TopLayer implements ImmutableLayer {
    private final Form form;

    public TopLayer(Form form) {
        this.form = form;
    }

    @Override
    public Anchor anchor() {
        return Anchor.TOP_LEFT;
    }

    @Override
    public Position position() {
        return Position.ZERO;
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public ItemStack display(Position position) {
        return null;
    }

    @Override
    public Form form() {
        return form;
    }
}
