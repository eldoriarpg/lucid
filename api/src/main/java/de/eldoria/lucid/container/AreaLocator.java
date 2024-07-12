package de.eldoria.lucid.container;

public interface AreaLocator {
    Area locate(Formed outer, Formed inner, Position position);
}
