package de.eldoria.lucid.scroller.impl.jumper;

import de.eldoria.lucid.scroller.ScrollJumper;
import de.eldoria.lucid.window.Sized;

public class PageScrollJumper implements ScrollJumper {
    @Override
    public int lines(Sized sized) {
        return sized.size();
    }
}