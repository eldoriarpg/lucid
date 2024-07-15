package de.eldoria.lucid;

import de.eldoria.lucid.scene.Scene;
import de.eldoria.lucid.services.LucidService;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Lucid {
    private static LucidService lucidService;

    public static void init(Plugin plugin) {
        lucidService = LucidService.create(plugin);
    }

    public static void transition(Player player, Scene scene) {
        lucidService.transition(player, scene);
    }

    public static void open(Player player, Scene scene) {
        lucidService.open(player, scene);
    }
}
