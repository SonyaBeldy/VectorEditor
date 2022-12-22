package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface IDrawer {
    void draw(GraphicsContext graphicsContext, Color color);
}