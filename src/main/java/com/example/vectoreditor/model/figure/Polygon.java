package com.example.vectoreditor.model.figure;

import com.example.vectoreditor.model.drawer.PolygonDrawer;
import javafx.scene.paint.Color;

public class Polygon extends Polyline {
    public Polygon(Color strokeColor) {
        super(strokeColor);
        name = "Polygon";
        drawer = new PolygonDrawer(points);
    }
}
