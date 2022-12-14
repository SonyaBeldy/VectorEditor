package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Action;
import com.example.vectoreditor.model.Figure;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CanvasController {
    private final Canvas drawCanvas;
    private Figure currentFigure;
    private final ArrayList<Figure> figures;

    private Color fillColor;

    private final ArrayList<Action> actions;

    public CanvasController(Canvas drawCanvas) {
        this.drawCanvas = drawCanvas;
        figures = new ArrayList<>();
        actions = new ArrayList<>();
        fillColor = Color.BLACK;
    }

    public void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        if (currentFigure != null) {
            System.out.println("null");
            currentFigure.drawHitbox(drawCanvas.getGraphicsContext2D());
        }
        for (Figure figure : figures) {
            figure.draw(drawCanvas.getGraphicsContext2D());
        }
    }

    public Figure whatWasClickedOn(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        for (int i = figures.size() - 1; i >= 0; i--) {
            if(figures.get(i).isClickedOn(x, y)){
                return figures.get(i);
            }
        }
        return null;
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    public Canvas getDrawCanvas() {
        return drawCanvas;
    }

    public ArrayList<Figure> getFigures() {
        return figures;
    }
    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}
