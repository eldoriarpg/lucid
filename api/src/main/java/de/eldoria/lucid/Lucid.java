package de.eldoria.lucid;

import de.eldoria.lucid.scene.Scene;
import de.eldoria.lucid.services.SceneService;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Lucid {
    private static SceneService sceneService;

    public static void init(Plugin plugin) {
        sceneService = SceneService.create(plugin);
    }

    public static void transition(Player player, Scene scene) {
        sceneService.transition(player, scene);
    }

    public static void open(Player player, Scene scene) {
        sceneService.open(player, scene);
    }
}
