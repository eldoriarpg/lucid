package de.eldoria.lucid.container;

/**
 * Describes a sized entry
 */
public interface Sized {
    /**
     * The size of the element.
     * This represents the amount of available slots, that can be defined.
     *
     * @return number of slots.
     */
    int size();
}
