package com.example.vectoreditor.model.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Drawer {
    void draw(GraphicsContext graphicsContext, Color color);
}
