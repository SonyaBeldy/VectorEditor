package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Polyline extends Figure{

    ArrayList<Line> lines;

    Color fillColor;

    public Polyline(Color fillColor) {
        this.fillColor = fillColor;
        lines = new ArrayList<>();

    }

    public void draw(GraphicsContext graphicsContext) {

        graphicsContext.setStroke(Paint.valueOf(String.valueOf(fillColor)));
        for (int i = 0; i < lines.size() - 1; i++) {
            lines.get(i).draw(graphicsContext);
        }
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void addLine(Line line) {
        lines.add(line);
    }
}
