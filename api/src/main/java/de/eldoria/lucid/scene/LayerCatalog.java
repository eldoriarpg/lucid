package de.eldoria.lucid.scene;

import de.eldoria.lucid.layer.FormHolder;
import de.eldoria.lucid.layer.Layer;
import de.eldoria.lucid.layer.Position;
import de.eldoria.lucid.layer.impl.misc.TopLayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class LayerCatalog implements FormHolder {
    private final Layer topLayer;
    private final List<Layer> layers;
    private final Map<Position, Layer> positionLayerList = new HashMap<>();
    private final Map<UUID, Layer> ids = new HashMap<>();
    private final Map<UUID, List<Position>> layerPositionMap = new HashMap<>();

    public LayerCatalog(Form formed, List<Layer> layers) {
        topLayer = new TopLayer(formed);
        this.layers = layers;
    }

    public Layer topLayer() {
        return topLayer;
    }

    public Position toLayerPosition(Layer layer, Position position) {
        return topLayer.toLayerPosition(layer, position);
    }

    public Optional<Layer> layerAtPosition(Position position) {
        return Optional.ofNullable(positionLayerList.get(position));
    }

    public void calculate(Scene scene) {
        for (Layer layer : layers) {
            ids.put(layer.uid(), layer);
            layer.registry().register(scene);
            for (Position position : topLayer.area(layer)) {
                positionLayerList.compute(position, (p, l) -> {
                    if (l == null) return layer;
                    // If the layers have the same priority, the later one takes precedence as if they were stacked.
                    if (l.priority() == layer.priority()) return layer;
                    return l.priority() < layer.priority() ? l : layer;
                });
            }
        }
        for (var entry : positionLayerList.entrySet()) {
            layerPositionMap.computeIfAbsent(entry.getValue().uid(), k -> new ArrayList<>()).add(entry.getKey());
        }
    }

    @Override
    public Form form() {
        return topLayer.form();
    }

    public List<Position> layerPositions(UUID layer) {
        List<Position> positions = layerPositionMap.get(layer);
        Objects.requireNonNull(positions, "Layer with uid " + layer + " is not present.");
        return positions;
    }

    public List<Layer> layers() {
        return Collections.unmodifiableList(layers);
    }

    public Layer byUID(UUID layer) {
        return ids.get(layer);
    }
}
