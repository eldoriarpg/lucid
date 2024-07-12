package de.eldoria.lucid.container;

import de.eldoria.lucid.scene.Form;

public interface FormHolder extends Formed {
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
