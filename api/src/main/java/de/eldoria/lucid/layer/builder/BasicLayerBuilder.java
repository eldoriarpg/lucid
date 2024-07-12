package de.eldoria.lucid.layer.builder;

import de.eldoria.lucid.builder.SelfReturningBuilder;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.scene.Form;

public abstract class BasicLayerBuilder<T, V> implements SelfReturningBuilder<T, V> {
    protected final Form form;
    protected Position position = Position.ZERO;
    protected Anchor anchor = Anchor.TOP_LEFT;
    protected int priority = Integer.MAX_VALUE;

    public BasicLayerBuilder(Form form) {
        this.form = form;
    }

    public T position(Position position) {
        this.position = position;
        return self();
    }

    public T position(int x, int y) {
        return position(new Position(x, y));
    }

    public T priority(int priority) {
        this.priority = priority;
        return self();
    }

    public T anchor(Anchor anchor) {
        this.anchor = anchor;
        return self();
    }
}
