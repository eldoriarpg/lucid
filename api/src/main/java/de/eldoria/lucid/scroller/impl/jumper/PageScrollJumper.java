package de.eldoria.lucid.scroller.impl.jumper;

import de.eldoria.lucid.layer.Sized;
import de.eldoria.lucid.scroller.ScrollJumper;

public class PageScrollJumper implements ScrollJumper {
    @Override
    public int lines(Sized sized) {
        return sized.size();
    }
}
