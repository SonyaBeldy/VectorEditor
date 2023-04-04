package com.example.vectoreditor.model.figures;

import com.example.vectoreditor.model.drawers.PolygonDrawer;

public class Polygon extends Polyline {
    public Polygon(FigureDecorationData figureDecorationData) {
        super(figureDecorationData);
        name = "Polygon";
        drawer = new PolygonDrawer(points);
    }
}
