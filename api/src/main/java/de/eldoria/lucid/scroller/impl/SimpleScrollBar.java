package de.eldoria.lucid.scroller.impl;

import de.eldoria.lucid.scroller.ScrollBar;
import de.eldoria.lucid.scroller.ScrollBarPosition;

public class SimpleScrollBar implements ScrollBar {
    @Override
    public ScrollBarPosition position() {
        return ScrollBarPosition.RIGHT;
    }

    @Override
    public int size() {
        return 0;
    }
}
