package com.example.vectoreditor.model.figure;

import com.example.vectoreditor.model.drawer.RectangleDrawer;
import javafx.scene.paint.Color;

public class Rectangle extends Polyline{
    public Rectangle(Color strokeColor) {
        super(strokeColor);
        name = "Rectangle";
        drawer = new RectangleDrawer(points);
    }

}
