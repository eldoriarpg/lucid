package de.eldoria.lucid.layer.impl.misc;

import de.eldoria.lucid.layer.ImmutableLayer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.layer.base.AbstractLayer;
import de.eldoria.lucid.scene.Form;
import org.bukkit.inventory.ItemStack;

public class TopLayer extends AbstractLayer implements ImmutableLayer {

    public TopLayer(Form form) {
        super(form, Position.ZERO, Anchor.TOP_LEFT, 0);
    }

    @Override
    public ItemStack getDisplay(Position position) {
        return null;
    }
}
