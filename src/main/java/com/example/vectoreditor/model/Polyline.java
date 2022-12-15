package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Polyline extends Figure{

    ArrayList<Line> lines;

    Color fillColor;

    public Polyline(Line line, Color fillColor) {
        this.fillColor = fillColor;

        lines.add(line);
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(Paint.valueOf(String.valueOf(fillColor)));
        graphicsContext.getCanvas().getGraphicsContext2D().strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(),  endPoint.getY());
    }


}
