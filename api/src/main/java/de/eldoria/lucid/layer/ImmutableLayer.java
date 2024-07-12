package de.eldoria.lucid.layer;

import de.eldoria.lucid.events.LayerClickEvent;

public interface ImmutableLayer extends Layer {
    /**
     * Whether content of this layer is immutable and can not be modified by the player.
     *
     * @return true when immutable
     */
    default boolean immutable(){
        return true;
    }

    default void click(LayerClickEvent event){
        if (immutable()) event.event().setCancelled(true);
    }
}
