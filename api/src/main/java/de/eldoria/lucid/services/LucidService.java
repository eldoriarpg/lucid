package de.eldoria.lucid.services;

import de.eldoria.lucid.scene.Scene;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface LucidService {
    static LucidService create(Plugin plugin) {
        LucidServiceImpl service = new LucidServiceImpl(plugin);
        plugin.getServer().getPluginManager().registerEvents(service, plugin);
        return service;
    }

    void transition(Player player, Scene scene);

    void open(Player player, Scene scene);
}
