package de.eldoria.lucid;

import de.eldoria.eldoutilities.plugin.EldoPlugin;
import de.eldoria.lucid.commands.BaseCommand;
import de.eldoria.lucid.services.SceneService;

import java.util.logging.Level;

public class LucidPlugin extends EldoPlugin {
    private SceneService sceneService;

    @Override
    public Level getLogLevel() {
        return Level.INFO;
    }

    @Override
    public void onPluginEnable() throws Throwable {
        sceneService = SceneService.create(this);
        registerCommand(new BaseCommand(this, sceneService));
    }
}
