package com.example.vectoreditor.model.figures;

import com.example.vectoreditor.model.Cloneable;
import javafx.scene.paint.Color;

import java.util.Optional;

public class FigureDecorationData implements Cloneable<FigureDecorationData> {

    private Optional<Color> fillColor;
    private Optional<Color> strokeColor;
    private double strokeWidth = 1;

    private double lineDashes = 0;
    //    private StrokeType strokeType;
    private double opacity;

    public FigureDecorationData(Optional<Color> strokeColor, Optional<Color> fillColor, double strokeWidth) {
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;
    }
    public FigureDecorationData(Optional<Color> strokeColor, Optional<Color> fillColor, double strokeWidth, double lineDashes) {
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;
        this.lineDashes = lineDashes;
    }
    public Optional<Color> getFillColor() {
        return fillColor;
    }

    public void setFillColor(Optional<Color> fillColor) {
        this.fillColor = fillColor;
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

    public double getLineDashes() {
        return lineDashes;
    }

    public void setLineDashes(double lineDashes) {
        this.lineDashes = lineDashes;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    @Override
    public FigureDecorationData clone() {
        return new FigureDecorationData(strokeColor, fillColor, strokeWidth);
    }
}
