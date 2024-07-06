package de.eldoria.lucid.scroller;

import de.eldoria.lucid.window.Sized;

public interface ScrollJumper {
    /**
     * Amount of lines a click on a scroll button will do.
     *
     * @return amount of lines
     */
    int lines(Sized sized);
}
