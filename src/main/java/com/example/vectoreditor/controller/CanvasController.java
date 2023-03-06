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
import java.util.Optional;

public class CanvasController {

    private final Canvas drawCanvas;
    private final BordersPainter bordersPainter;
    private ITool currentTool;
    private Optional<Figure> currentFigure = Optional.empty();
    private Color strokeColor;
    private final LayerList layers;
    private Layer currentLayer;
    private final ArrayList<Action> actions;

    public CanvasController(Canvas drawCanvas) {
        this.drawCanvas = drawCanvas;
        layers = new LayerList(1);
        currentLayer = layers.getLast().orElseThrow();
        actions = new ArrayList<>();

        strokeColor = Color.BLACK;
        bordersPainter = new BordersPainter(drawCanvas.getGraphicsContext2D());
    }

    public void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (int i = 0; i < layers.getSize(); i++) {
            Layer layer = layers.get(i);
            for (int j = 0; j < layer.getObjectsCount(); j++) {
                layer.getFigure(j).draw(drawCanvas.getGraphicsContext2D());
            }
        }
        currentFigure.ifPresent(bordersPainter::drawBoards);
    }

    public Optional<Figure> whatWasClickedOn(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        for (int i = layers.getSize() - 1; i >= 0; i--) {
            Layer layer = layers.get(i);
            for (int j = 0; j < layer.getObjectsCount(); j++) {
                Figure figure = layer.getFigure(j);
                if(figure.isClickedOn(x, y)){
                    return Optional.of(figure);
                }
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

    public void addFigure(Figure figure) {
        currentLayer.addFigure(figure);
    }

    public void removeFigure(int ind) {
        currentLayer.remove(ind);
    }
    
    public boolean isEmpty() { //нет фигур вообще
        for (int i = 0; i < layers.getSize(); i++) {
            if(layers.get(i).getObjectsCount() >= 0) {
                return false;
            }
        }
        return true;
    }

    public Layer getCurrentLayer() {
        return currentLayer;
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
