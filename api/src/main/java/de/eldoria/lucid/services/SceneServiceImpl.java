package de.eldoria.lucid.services;

import de.eldoria.lucid.scene.Scene;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SceneServiceImpl implements Listener, SceneService {
    private final Plugin plugin;
    /**
     * The open scene of the player
     */
    private final Map<UUID, Session> open = new HashMap<>();
    /**
     * The scene a player is about to transition to on the next occasion.
     */
    private final Map<UUID, Scene> transition = new HashMap<>();

    SceneServiceImpl(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSceneClose(InventoryCloseEvent event) {
        HumanEntity player = event.getPlayer();

        if (transition.containsKey(player.getUniqueId())) return;

        Session session = open.remove(player.getUniqueId());
        if (session != null) {
            session.scene().close((Player) player);
        }
    }

    @EventHandler
    public void onInventoryMove(InventoryMoveItemEvent event) {
        plugin.getLogger().info("Moved item");
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event) {
        plugin.getLogger().info("Inventory interaction");
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null || clickedInventory.getType() == InventoryType.PLAYER) return;
        // TODO: This might be a bad idea since it does not prevent moving items into the inventory
        if (event.getView().getTopInventory() != event.getClickedInventory()) return;
        if (!(event.getWhoClicked() instanceof Player player)) return;
        Session session = open.get(player.getUniqueId());
        if (session == null) return;
        session.scene().click(player, event);
        plugin.getLogger().info("Inventory click");
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        // TODO: Consider reopening on player connect.
        open.remove(event.getPlayer().getUniqueId());
        transition.remove(event.getPlayer().getUniqueId());
    }

    @Override
    public void transition(Player player, Scene scene) {
        Session session = open.get(player.getUniqueId());
        if (session != null) {
            if (session.inventory().getSize() == scene.size()) {
                open.put(player.getUniqueId(), new Session(session.inventory(), scene));
                scene.apply(session.inventory());
                return;
            }
        }
        transition.put(player.getUniqueId(), scene);
        player.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
        openDelayed(player, scene);
    }

    private void openDelayed(Player player, Scene scene) {
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> open(player, scene), 1);
    }

    @Override
    public void open(Player player, Scene scene) {
        Inventory inventory = plugin.getServer().createInventory(null, scene.size());
        scene.apply(inventory);
        open.put(player.getUniqueId(), new Session(inventory, scene));
        player.openInventory(inventory).setTitle(scene.title());
    }

    private record Session(Inventory inventory, Scene scene) {
    }
}
