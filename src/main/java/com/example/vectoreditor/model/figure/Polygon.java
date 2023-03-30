package com.example.vectoreditor.model.figure;

import com.example.vectoreditor.model.drawer.PolygonDrawer;
import javafx.scene.paint.Color;

public class Polygon extends Polyline {
    public Polygon(FigureDecorationData figureDecorationData) {
        super(figureDecorationData);
        name = "Polygon";
        drawer = new PolygonDrawer(points);
    }
}
