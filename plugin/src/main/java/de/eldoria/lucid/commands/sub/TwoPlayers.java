package de.eldoria.lucid.commands.sub;

import de.eldoria.eldoutilities.commands.command.AdvancedCommand;
import de.eldoria.eldoutilities.commands.command.CommandMeta;
import de.eldoria.eldoutilities.commands.command.util.Arguments;
import de.eldoria.eldoutilities.commands.exceptions.CommandException;
import de.eldoria.eldoutilities.commands.executor.IPlayerTabExecutor;
import de.eldoria.lucid.Lucid;
import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.impl.container.ItemStackContainerLayer;
import de.eldoria.lucid.layer.impl.decoration.StaticDecoration;
import de.eldoria.lucid.layer.impl.delegates.ImmutabilityLayer;
import de.eldoria.lucid.layer.impl.delegates.RelocationLayer;
import de.eldoria.lucid.scene.Form;
import de.eldoria.lucid.scene.Scene;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class TwoPlayers extends AdvancedCommand implements IPlayerTabExecutor {
    public TwoPlayers(Plugin plugin) {
        super(plugin, CommandMeta.builder("twoplayer")
                .addArgument("player", true)
                .build());
    }

@Override
public void onCommand(@NotNull Player player, @NotNull String alias, @NotNull Arguments args) throws CommandException {
    var other = args.asPlayer(0);

    var left = new Position(1, 1);
    var right = new Position(5, 1);

    ItemStackContainerLayer firstContainer = ItemStackContainerLayer.builder(new Form(3, 4))
            .position(left)
            .build();

    ItemStackContainerLayer secondContainer = ItemStackContainerLayer.builder(new Form(3, 4))
            .position(left)
            .build();

    var first = Scene.builder(6)
            .title("first")
            .add(StaticDecoration.builder(new Form(9, 6), Material.BLUE_STAINED_GLASS_PANE).build())
            .add(firstContainer)
            .add(secondContainer.relocate(right).makeImmutable())
            .inputSink(firstContainer)
            .build();

    var second = Scene.builder(6)
            .title("second")
            .add(StaticDecoration.builder(new Form(9, 6), Material.RED_STAINED_GLASS_PANE).build())
            .add(secondContainer)
            .add(firstContainer.relocate(right).makeImmutable())
            .inputSink(secondContainer)
            .build();

    Lucid.open(player, first);
    Lucid.open(other, second);
}
}
