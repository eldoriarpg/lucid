package de.eldoria.lucid;

import de.eldoria.eldoutilities.messages.MessageSender;
import de.eldoria.eldoutilities.plugin.EldoPlugin;
import de.eldoria.lucid.commands.BaseCommand;

import java.util.logging.Level;

public class LucidPlugin extends EldoPlugin {

    @Override
    public Level getLogLevel() {
        return Level.INFO;
    }

    @Override
    public void onPluginEnable() throws Throwable {
        Lucid.init(this);
        registerCommand(new BaseCommand(this));
        MessageSender.builder(this).register();
    }


}
