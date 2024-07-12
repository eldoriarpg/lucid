package de.eldoria.lucid.container;

import de.eldoria.lucid.scene.Form;

public interface Container extends Formed {
    default int size() {
        return form().horizontal() * form().vertical();
    }

    Form form();

    @Override
    default int horizontal() {
        return form().horizontal();
    }

    @Override
    default int vertical() {
        return form().vertical();
    }
}
