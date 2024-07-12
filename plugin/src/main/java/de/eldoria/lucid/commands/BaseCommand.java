package de.eldoria.lucid.commands;

import de.eldoria.eldoutilities.commands.command.AdvancedCommand;
import de.eldoria.eldoutilities.commands.command.CommandMeta;
import de.eldoria.eldoutilities.commands.command.util.Arguments;
import de.eldoria.eldoutilities.commands.exceptions.CommandException;
import de.eldoria.eldoutilities.commands.executor.IPlayerTabExecutor;
import de.eldoria.lucid.scene.Scene;
import de.eldoria.lucid.services.SceneService;
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
        Scene scene = Scene.builder(2).build();
        sceneService.open(player, scene);
    }
}
