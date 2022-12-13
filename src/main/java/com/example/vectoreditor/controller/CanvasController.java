package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class CanvasController {
    private final Canvas drawCanvas;
    private Figure currentFigure;
    private final ArrayList<Figure> figures;

    public CanvasController(Canvas drawCanvas) {
        this.drawCanvas = drawCanvas;
        figures = new ArrayList<>();
    }

    public void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (Figure figure : figures) {
            figure.draw(drawCanvas.getGraphicsContext2D());
        }
    }
    public void redrawFiguresWithoutCurrant() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (Figure figure : figures) {
            if (figure != currentFigure) {
                figure.draw(drawCanvas.getGraphicsContext2D());
            }

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

}
