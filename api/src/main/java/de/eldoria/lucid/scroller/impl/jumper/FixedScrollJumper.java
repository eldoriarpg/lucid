package de.eldoria.lucid.scroller.impl.jumper;

import de.eldoria.lucid.scroller.ScrollJumper;
import de.eldoria.lucid.window.Sized;

public class FixedScrollJumper implements ScrollJumper {

    private final int size;

    public FixedScrollJumper(int size) {
        this.size = size;
    }

    @Override
    public int lines(Sized sized) {
        return size;
    }
}
