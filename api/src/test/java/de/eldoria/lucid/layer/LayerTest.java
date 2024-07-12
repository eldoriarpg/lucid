package de.eldoria.lucid.layer;

import de.eldoria.lucid.events.LayerClickEvent;
import de.eldoria.lucid.layer.anchor.Anchor;
import de.eldoria.lucid.layer.impl.misc.TopLayer;
import de.eldoria.lucid.scene.Form;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LayerTest {
    Layer outer = new TopLayer(new Form(10,10));
    Layer inner = new Layer() {
        @Override
        public Anchor anchor() {
            return Anchor.TOP_LEFT;
        }

        @Override
        public Position position() {
            return Position.ONE;
        }

        @Override
        public int priority() {
            return 0;
        }

        @Override
        public org.bukkit.inventory.ItemStack getDisplay(Position position) {
            return null;
        }

        @Override
        public void click(LayerClickEvent event) {

        }

        @Override
        public Form form() {
            return new Form(5,5);
        }
    };

    @Test
    void area() {
        Area area = outer.area(inner);
        Assertions.assertEquals(new Area(Position.ONE, Position.ONE.plus(4)), area);
    }

    @Test
    void toLayerPosition() {
        Position layerPosition = outer.toLayerPosition(inner, Position.ONE);
        Assertions.assertEquals(Position.ZERO, layerPosition);
    }
}
