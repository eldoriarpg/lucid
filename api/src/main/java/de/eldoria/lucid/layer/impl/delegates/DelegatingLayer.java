package de.eldoria.lucid.layer.impl.delegates;

import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.scene.Form;
import de.eldoria.lucid.scene.SceneRegistry;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class DelegatingLayer implements Layer {
    protected final Layer layer;

    public DelegatingLayer(Layer layer) {
        this.layer = layer;
    }

    @Override
    public SceneRegistry registry() {
        return layer.registry();
    }

    @Override
    public Anchor anchor() {
        return layer.anchor();
    }

    @Override
    public Position position() {
        return layer.position();
    }

    @Override
    public int priority() {
        return layer.priority();
    }

    @Override
    public ItemStack displayAt(Position position) {
        return layer.displayAt(position);
    }

    @Override
    public void click(LayerClickEvent event) {
        layer.click(event);
    }

    @Override
    public Form form() {
        return layer.form();
    }

    @Override
    public UUID uid() {
        return layer.uid();
    }
}
