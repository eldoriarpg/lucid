package de.eldoria.lucid.container;

/**
 * Describes the form of a rectangular shaped entry.
 */
public interface Formed extends Sized {
    /**
     * The slots available on the horizontal lines.
     *
     * @return available slots
     */
    int horizontal();

    /**
     * The slots available on the vertical lines.
     *
     * @return available slots
     */
    int vertical();

    /**
     * The size of the formed entry. This is {@link #horizontal()} * {@link #vertical()} by default.
     *
     * @return number of slots.
     */
    @Override
    default int size() {
        return horizontal() * vertical();
    }
}
