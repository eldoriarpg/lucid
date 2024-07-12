package de.eldoria.lucid.layer;

import de.eldoria.lucid.scene.Scene;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface ImmutableLayer extends Layer {
    /**
     * Whether content of this layer is immutable and can not be modified by the player.
     *
     * @return true when immutable
     */
    default boolean immutable(){
        return true;
    }

    default void click(Scene scene, Player player, InventoryClickEvent event){
        if (immutable()) event.setCancelled(true);
    }
}
