package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Action;
import com.example.vectoreditor.model.BordersPainter;
import com.example.vectoreditor.model.Layer;
import com.example.vectoreditor.model.LayerList;
import com.example.vectoreditor.model.figure.Figure;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CanvasController {

    private final Canvas drawCanvas;
    private final BordersPainter bordersPainter;
    private ITool currentTool;
    private Optional<Figure> currentFigure = Optional.empty();
    private final ArrayList<Figure> figures;
    private Color strokeColor;
    private final LayerList layers;
    private Layer currentLayer;
    private final ArrayList<Action> actions;

    public CanvasController(Canvas drawCanvas) {
        this.drawCanvas = drawCanvas;
        figures = new ArrayList<>();
        layers = new LayerList(1);
        currentLayer = layers.getLast().orElseThrow();
        actions = new ArrayList<>();

        strokeColor = Color.BLACK;
        bordersPainter = new BordersPainter(drawCanvas.getGraphicsContext2D());
    }

    public void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (Figure figure : figures) {
            figure.draw(drawCanvas.getGraphicsContext2D());
        }
        currentFigure.ifPresent(bordersPainter::drawBoards);
    }

    public Optional<Figure> whatWasClickedOn(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        for (int i = figures.size() - 1; i >= 0; i--) {
            if(figures.get(i).isClickedOn(x, y)){
                return Optional.of(figures.get(i));
            }
        }
        return Optional.empty();
    }

    public Optional<Figure> getCurrentFigure() {
        return currentFigure;
    }

    public void setCurrentFigure(Optional<Figure> currentFigure) {
        this.currentFigure = currentFigure;
        if (currentFigure.isEmpty()) {
            return;
        }
        redrawAllFigures();
        bordersPainter.drawBoards(currentFigure.get());
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
