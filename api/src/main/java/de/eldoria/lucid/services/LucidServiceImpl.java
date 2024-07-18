package de.eldoria.lucid.services;

import de.eldoria.lucid.scene.Scene;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LucidServiceImpl implements Listener, LucidService, Runnable {
    private final Plugin plugin;
    /**
     * The open scene of the player
     */
    private final Map<UUID, Session> open = new HashMap<>();
    /**
     * The scene a player is about to transition to on the next occasion.
     */
    private final Map<UUID, Scene> transition = new HashMap<>();

    LucidServiceImpl(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getScheduler().runTaskTimer(plugin, this, 1, 1);
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
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getView().getBottomInventory() == event.getInventory()) {
            // Let the player do what they want in their inv
            return;
        }
        if (open.containsKey(event.getWhoClicked().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return;

        if (clickedInventory.getType() != InventoryType.PLAYER && clickedInventory.getType() != InventoryType.CHEST)
            return;

        if (!(event.getWhoClicked() instanceof Player player)) return;
        Session session = open.get(player.getUniqueId());
        if (session == null) return;

        // Handle the players personal inventory
        if (event.getView().getBottomInventory() == event.getClickedInventory()) {
            ItemStack currentItem = event.getCurrentItem();
            // check whether the player wants to move an item to our inventory
            if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT && currentItem != null) {
                ItemStack result = session.scene().putItem(currentItem.clone());
                event.setCurrentItem(result);
                // Let's not support this for now.
                // TODO: Implement moving items with shift into the found container
                event.setCancelled(true);
            }
            return;
        }

        // This should prob never happen
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
        Inventory inventory;
        if (scene.titleComponent() != null) {
            inventory = plugin.getServer().createInventory(null, scene.size(), scene.titleComponent());
        } else if (scene.title() != null) {
            inventory = plugin.getServer().createInventory(null, scene.size(), scene.title());
        } else {
            inventory = plugin.getServer().createInventory(null, scene.size());
        }
        scene.apply(inventory);
        open.put(player.getUniqueId(), new Session(inventory, scene));
        InventoryView view = player.openInventory(inventory);
    }

    @Override
    public void run() {
        for (Session value : open.values()) {
            value.scene().tick();
        }
    }

    private record Session(Inventory inventory, Scene scene) {
    }
}
