package de.eldoria.lucid.commands;

import de.eldoria.eldoutilities.commands.command.AdvancedCommand;
import de.eldoria.eldoutilities.commands.command.CommandMeta;
import de.eldoria.eldoutilities.commands.command.util.Arguments;
import de.eldoria.eldoutilities.commands.exceptions.CommandException;
import de.eldoria.eldoutilities.commands.executor.IPlayerTabExecutor;
import de.eldoria.lucid.layer.impl.decoration.StaticDecoration;
import de.eldoria.lucid.scene.Form;
import de.eldoria.lucid.scene.Scene;
import de.eldoria.lucid.services.SceneService;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class BaseCommand extends AdvancedCommand implements IPlayerTabExecutor {
    private final SceneService sceneService;

    public BaseCommand(Plugin plugin, SceneService sceneService) {
        super(plugin, CommandMeta.builder("lucid").build());
        this.sceneService = sceneService;
    }

    @Override
    public void onCommand(@NotNull Player player, @NotNull String alias, @NotNull Arguments args) throws CommandException {
Scene scene = Scene.builder(new Form(9,2))
        .title("Lucid Inventory")
        .add(StaticDecoration.builder(new Form(9, 2), Material.CYAN_STAINED_GLASS_PANE))
        .add(StaticDecoration.builder(new Form(1, 2), Material.YELLOW_STAINED_GLASS_PANE).position(1, 0))
        .add(StaticDecoration.builder(new Form(1, 2), Material.RED_STAINED_GLASS_PANE).position(3, 0))
        .add(StaticDecoration.builder(new Form(1, 2), Material.BLUE_STAINED_GLASS_PANE).position(5, 0))
        .add(StaticDecoration.builder(new Form(1, 2), Material.GREEN_STAINED_GLASS_PANE).position(7, 0))
        .add(StaticDecoration.builder(new Form(1, 2), Material.WHITE_STAINED_GLASS_PANE).position(9, 0))
        .build();
sceneService.open(player, scene);
    }
}
