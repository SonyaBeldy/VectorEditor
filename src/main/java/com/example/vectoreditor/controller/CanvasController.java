package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Action;
import com.example.vectoreditor.model.BordersPainter;
import com.example.vectoreditor.model.Figure;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CanvasController {

    private final Canvas drawCanvas;

    private ITool currentTool;
    private Figure currentFigure;
    private final ArrayList<Figure> figures;

    private Color strokeColor;

    private final ArrayList<Action> actions;

    private final BordersPainter bordersPainter;

    public CanvasController(Canvas drawCanvas) {
        this.drawCanvas = drawCanvas;
        figures = new ArrayList<>();
        actions = new ArrayList<>();
        strokeColor = Color.BLACK;
        bordersPainter = new BordersPainter(drawCanvas.getGraphicsContext2D());

    }

    public void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        if (currentFigure != null) {
            bordersPainter.drawBoards(currentFigure);
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
        if (currentFigure == null) {
            return;
        }
        redrawAllFigures();
        bordersPainter.drawBoards(currentFigure);
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

    public ITool getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(ITool currentTool) {
        this.currentTool = currentTool;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }
}
