package de.eldoria.lucid.scene;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SceneRegistry {
    private final Set<Scene> scenes = new HashSet<>();

    void register(Scene scene) {
        scenes.add(scene);
    }

    public Set<Scene> scenes() {
        return Collections.unmodifiableSet(scenes);
    }
}
