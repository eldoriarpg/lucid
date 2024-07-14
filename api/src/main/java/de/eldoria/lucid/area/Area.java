package de.eldoria.lucid.area;

import de.eldoria.lucid.layer.Position;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * An area describes the space a layers takes up.
 * This area might have any shape.
 */
public interface Area extends Iterable<Position> {
    /**
     * An iterator containing all positions that this area occupies.
     *
     * @return iterator
     */
    @NotNull
    @Override
    Iterator<Position> iterator();
}
