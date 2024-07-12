package de.eldoria.lucid.builder;

/**
 * A basic builder that returns itself
 *
 * @param <T> type of builder
 * @param <V> type of result of the builder
 */
public interface SelfReturningBuilder<T, V> extends Buildable<V> {
    @SuppressWarnings("unchecked")
    default T self() {
        return (T) this;
    }
}
