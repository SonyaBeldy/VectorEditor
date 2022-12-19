package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;

public interface Targetable {
    boolean isClickedOn(double x, double y);
    void highlight(GraphicsContext graphicsContext);
    void drawBorders(GraphicsContext graphicsContext);

}
