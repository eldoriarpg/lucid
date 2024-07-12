package de.eldoria.lucid.exceptions;

import de.eldoria.lucid.container.Formed;
import de.eldoria.lucid.exceptions.ex.InvalidShapeException;
import de.eldoria.lucid.exceptions.ex.InvalidSizeException;

public final class Checks {
    public static void assertSceneForm(Formed form) {
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

    public static void assertNotEvenForm(Formed form) {
        if (form.horizontal() % 2 == 0) {
            throw new InvalidShapeException("Shape has to be an uneven number on horizontal axis.");
        }
        if (form.vertical() % 2 == 0) {
            throw new InvalidShapeException("Shape has to be an uneven number on vertical axis.");
        }
    }
}
