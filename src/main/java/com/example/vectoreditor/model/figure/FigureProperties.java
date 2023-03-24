package com.example.vectoreditor.model.figure;

import javafx.scene.paint.Color;

import java.util.Optional;

public class FigureProperties {

    private Optional<Color> fill;
    private Optional<Color> strokeColor;
    private double strokeWidth = 1;
    //    private StrokeType strokeType;
    private double opacity;

    public Optional<Color> getFill() {
        return fill;
    }

    public void setFill(Optional<Color> fill) {
        this.fill = fill;
    }

    public Optional<Color> getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Optional<Color> strokeColor) {
        this.strokeColor = strokeColor;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }


}
