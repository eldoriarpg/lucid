package de.eldoria.lucid.commands.sub;

import de.eldoria.eldoutilities.commands.command.AdvancedCommand;
import de.eldoria.eldoutilities.commands.command.CommandMeta;
import de.eldoria.eldoutilities.commands.command.util.Arguments;
import de.eldoria.eldoutilities.commands.exceptions.CommandException;
import de.eldoria.eldoutilities.commands.executor.IPlayerTabExecutor;
import de.eldoria.lucid.Lucid;
import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.impl.StaticDisplay;
import de.eldoria.lucid.layer.impl.actions.Action;
import de.eldoria.lucid.layer.impl.actions.ActionLayer;
import de.eldoria.lucid.layer.impl.decoration.StaticDecoration;
import de.eldoria.lucid.scene.Form;
import de.eldoria.lucid.scene.Scene;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Test extends AdvancedCommand implements IPlayerTabExecutor {
    public Test(Plugin plugin) {
        super(plugin, CommandMeta.builder("test")
                .build());
    }

    @Override
    public void onCommand(@NotNull Player player, @NotNull String alias, @NotNull Arguments args) throws CommandException {
        StaticDecoration border = StaticDecoration.builder(new Form(9, 6), Material.CYAN_STAINED_GLASS_PANE).build();
        ActionLayer actionLayer = ActionLayer.builder(new Form(7, 4)).position(1, 1)
                .action(new Position(0, 0), new ColorChangeAction(Material.CYAN_STAINED_GLASS_PANE, border))
                .action(new Position(1, 0), new ColorChangeAction(Material.GREEN_STAINED_GLASS_PANE, border))
                .action(new Position(2, 0), new ColorChangeAction(Material.BLACK_STAINED_GLASS_PANE, border))
                .action(new Position(3, 0), new ColorChangeAction(Material.RED_STAINED_GLASS_PANE, border))
                .action(new Position(4, 0), new ColorChangeAction(Material.ORANGE_STAINED_GLASS_PANE, border))
                .action(new Position(5, 0), new ColorChangeAction(Material.LIGHT_BLUE_STAINED_GLASS_PANE, border))
                .action(new Position(6, 0), new ColorChangeAction(Material.LIGHT_GRAY_STAINED_GLASS_PANE, border))
                .action(new Position(7, 0), new ColorChangeAction(Material.PURPLE_STAINED_GLASS_PANE, border))
                .build();
        Scene scene = Scene.builder(6)
                .title("Lucid Inventory")
                .add(border)
                .add(actionLayer)
                .build();
        Lucid.open(player, scene);
    }

    private static class ColorChangeAction extends Action {
        private final StaticDisplay decoration;

        public ColorChangeAction(Material display, StaticDisplay decoration) {
            super(ItemStack.of(display));
            this.decoration = decoration;
        }

        @Override
        public void click(LayerClickEvent event) {
            decoration.updateDisplay(getDisplay());
            decoration.redraw();
            event.event().setCancelled(true);
        }
    }
}
