package de.eldoria.lucid.commands;

import de.eldoria.eldoutilities.commands.command.AdvancedCommand;
import de.eldoria.eldoutilities.commands.command.CommandMeta;
import de.eldoria.lucid.commands.sub.Test;
import de.eldoria.lucid.commands.sub.TwoPlayers;
import org.bukkit.plugin.Plugin;

public class BaseCommand extends AdvancedCommand {
    public BaseCommand(Plugin plugin) {
        super(plugin, CommandMeta.builder("lucid")
                .withSubCommand(new Test(plugin))
                .withSubCommand(new TwoPlayers(plugin))
                .build());
    }
}
