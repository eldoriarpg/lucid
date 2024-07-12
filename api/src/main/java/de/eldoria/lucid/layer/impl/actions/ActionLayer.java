package de.eldoria.lucid.layer.impl.actions;

import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.layer.base.AbstractLayer;
import de.eldoria.lucid.layer.builder.BasicLayerBuilder;
import de.eldoria.lucid.scene.Form;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ActionLayer extends AbstractLayer {

    private final Map<Position, ? extends Action> actions;

    ActionLayer(Form form, Position position, Anchor anchor, int priority, Map<Position, ? extends Action> actions) {
        super(form, position, anchor, priority);
        this.actions = actions;
    }

    public static Builder builder(Form form) {
        return new Builder(form);
    }

    @Override
    public ItemStack getDisplay(Position position) {
        return positionalAction(position).map(Action::getDisplay).orElse(null);
    }

    @Override
    public void click(LayerClickEvent event) {
        positionalAction(event.position()).ifPresent(a -> a.click(event));
    }

    private Optional<Action> positionalAction(Position position) {
        return Optional.ofNullable(actions.get(position));
    }

    public static class Builder extends BasicLayerBuilder<Builder, ActionLayer> {

        private final Map<Position, Action> map = new HashMap<>();

        Builder(Form form) {
            super(form);
        }

        public Builder action(Position position, Action action) {
            map.put(position, action);
            return self();
        }

        @Override
        public ActionLayer build() {
            return new ActionLayer(form, position, anchor, priority, map);
        }
    }
}
