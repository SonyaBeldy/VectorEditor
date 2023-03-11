package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Action;
import com.example.vectoreditor.model.BordersPainter;
import com.example.vectoreditor.model.Layer;
import com.example.vectoreditor.model.figure.Figure;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class CanvasController {

    private final Canvas drawCanvas;
    private final BordersPainter bordersPainter;
    private ITool currentTool;
    private Optional<Figure> currentFigure = Optional.empty();
    private Color strokeColor;
    private final ArrayList<LayerBoxController> layers = new ArrayList<>();
    private Optional<LayerBoxController> currentLayer;
    private final ArrayList<Action> actions;

    public CanvasController(Canvas drawCanvas) {
        this.drawCanvas = drawCanvas;

        actions = new ArrayList<>();
        strokeColor = Color.BLACK;
        bordersPainter = new BordersPainter(drawCanvas.getGraphicsContext2D());
    }

    public ArrayList<LayerBoxController> getLayers() {
        return layers;
    }

    public void addFigureToCurrentLayerItemsList(Figure figure) {
        if (currentLayer.isPresent()) {
            currentLayer.get().getLayer().addFigure(figure);
        }
    }

    public void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i).getLayer();
            for (int j = 0; j < layer.getObjectsCount(); j++) {
                layer.getFigure(j).draw(drawCanvas.getGraphicsContext2D());
            }
        }
        currentFigure.ifPresent(bordersPainter::drawBoards);
    }

    public Optional<Figure> whatWasClickedOn(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        for (int i = layers.size() - 1; i >= 0; i--) {
            Layer layer = layers.get(i).getLayer();
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
        if (currentLayer.isPresent()) {
            currentLayer.get().addFigure(figure);
            addFigureToCurrentLayerItemsList(figure);
        }
    }
    
    public boolean isEmpty() {
        for (LayerBoxController layer : layers) {
            if (layer.getLayer().getObjectsCount() >= 0) {
                return false;
            }
        }
        return true;
    }

    public Optional<LayerBoxController> getCurrentLayer() {
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

    public void setCurrentLayer(LayerBoxController controller) {
        currentLayer = Optional.ofNullable(controller);
        currentLayer.ifPresent(LayerBoxController::highlight);
    }

    public Optional<LayerBoxController> getLastLayer() {
        if (layers.size() < 1) {
            return Optional.empty();
        } else {
            return Optional.of(layers.get(layers.size() - 1));
        }
    }

    public int getCurrentLayerInd() {
        if (currentLayer.isPresent()) {
            return layers.indexOf(currentLayer.get());
        } else {
            return -1;
        }
    }

    public void removeLayer(int ind) {
        layers.remove(ind);
    }
}
