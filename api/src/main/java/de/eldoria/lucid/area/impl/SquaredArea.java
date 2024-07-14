package de.eldoria.lucid.area.impl;

import de.eldoria.lucid.area.Area;
import de.eldoria.lucid.layer.Position;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.stream.IntStream;

public record SquaredArea(Position min, Position max) implements Area {
    @Override
    @NotNull
    public Iterator<Position> iterator() {
        return IntStream.rangeClosed(min.x(), max.x())
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(min.y(), max.y())
                        .mapToObj(y -> new Position(x, y))).iterator();
    }
}
