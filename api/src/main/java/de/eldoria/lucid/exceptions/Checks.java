package de.eldoria.lucid.exceptions;

import de.eldoria.lucid.exceptions.ex.InvalidSizeException;
import de.eldoria.lucid.scene.Form;

public final class Checks {
    public static void assertSceneForm(Form form) {
        if (form.vertical() < 1) {
            throw new InvalidSizeException("Vertical size is less than 1. Has to be between 1 and 6.");
        }
        if (form.vertical() > 6) {
            throw new InvalidSizeException("Vertical size is greater than 6. Has to be between 1 and 6.");
        }
        if (form.horizontal() != 9) {
            throw new InvalidSizeException("Horizontal size has to be 9.");
        }
    }
}
