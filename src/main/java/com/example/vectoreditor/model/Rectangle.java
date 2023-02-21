package com.example.vectoreditor.model;

import javafx.scene.paint.Color;

public class Rectangle extends Polyline{
    public Rectangle(Color strokeColor) {
        super(strokeColor);
        drawer = new RectangleDrawer(points);
    }

}
