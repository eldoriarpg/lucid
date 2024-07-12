package de.eldoria.lucid.scroller.impl.jumper;

import de.eldoria.lucid.container.Sized;
import de.eldoria.lucid.scroller.ScrollJumper;

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
