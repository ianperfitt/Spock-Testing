package org.example;

import org.example.Polygon;
import org.example.Renderer;

public class ShapeFactory {
    private final Renderer renderer;

    public ShapeFactory(Renderer renderer) {
        this.renderer = renderer;
    }

    public Polygon createDefaultPolygon() {
        return new Polygon(4, renderer);
    }
}