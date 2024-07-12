package de.eldoria.lucid.layer.base;

import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.scene.Form;
import de.eldoria.lucid.scene.SceneRegistry;

public abstract class AbstractLayer implements Layer {

    private final Form form;
    private final Position position;
    private final Anchor anchor;
    private final int priority;
    private final SceneRegistry registry = new SceneRegistry();

    public AbstractLayer(Form form, Position position, Anchor anchor, int priority) {
        this.form = form;
        this.position = position;
        this.anchor = anchor;
        this.priority = priority;
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

    @Override
    public Form form() {
        return form;
    }

    @Override
    public SceneRegistry registry() {
        return registry;
    }
}
