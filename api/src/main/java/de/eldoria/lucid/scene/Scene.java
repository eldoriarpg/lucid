package de.eldoria.lucid.scene;

import de.eldoria.lucid.container.Container;
import de.eldoria.lucid.exceptions.Checks;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * A scene that represents the view of a player.
 * <p>
 * One scene is equal to one gui, which may change during time or transition to another scene.
 */
public class Scene implements Container {
    private final Form form;

    private Scene(Form form) {
        this.form = form;
    }

    @Override
    public Form form() {
        return form;
    }

    public static Builder builder(int lines) {
        return Builder.create(lines);
    }

    public void transition(Scene scene) {
        // TODO:
    }

    public void close(Player player) {
        //TODO
    }

    public void apply(Inventory inventory) {
        // TODO: Set items corresponding to the containers.
    }

    public static class Builder {
        private final Form form;

        private Builder(Form form) {
            this.form = form;
        }

        public static Builder create(int lines) {
            return create(new Form(9, lines));
        }

        public static Builder create(Form form) {
            Checks.assertSceneForm(form);
            return new Builder(form);
        }

        public Scene build() {
            return new Scene(form);
        }
    }
}
