package de.eldoria.lucid.layer;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.stream.IntStream;

public record Area(Position min, Position max) implements Iterable<Position> {
    @Override
    @NotNull
    public Iterator<Position> iterator() {
        return IntStream.rangeClosed(min.x(), max.x())
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(min.y(), max.y())
                        .mapToObj(y -> new Position(x, y))).iterator();
    }
}
