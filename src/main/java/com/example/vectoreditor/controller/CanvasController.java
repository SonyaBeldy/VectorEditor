package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Action;
import com.example.vectoreditor.model.BordersPainter;
import com.example.vectoreditor.model.Layer;
import com.example.vectoreditor.model.LayerControllerList;
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
    //private final LayerList layers;
    //private LayerControllerList layerControllerList;
    private final ArrayList<LayerBoxController> layers = new ArrayList<>();

//    private Layer currentLayer;
    private LayerBoxController currentLayer;
    private final ArrayList<Action> actions;

    public CanvasController(Canvas drawCanvas) {
        this.drawCanvas = drawCanvas;

        //layerControllerList.createNewLayer();
        //addNewLayer();

        actions = new ArrayList<>();
        strokeColor = Color.BLACK;
        bordersPainter = new BordersPainter(drawCanvas.getGraphicsContext2D());
    }

    public ArrayList<LayerBoxController> getLayers() {
        return layers;
    }

    public void addFigureToCurrentLayerItemsList(Figure figure) {
        currentLayer.getLayer().addFigure(figure);
//        layerControllerList.get(layerItemsList.size() - 1).getItemController().addFigure(figure);
    }
//    public void addFigureToLastLayerItemsList(Figure figure) {
//        layerItemsList.get(layerItemsList.size() - 1).getItemController().addFigure(figure);
//    }
//    public ArrayList<LayerBoxController> getLayerItemsList() {
//        return layerControllerList;
//    }

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

//    public void addNewLayer() {
//        layerControllerList.addNewLayer();
//    }
//
//    public Optional<Layer> getNewLayer() {
//        return layers.getLast();
//    }
    public void addFigure(Figure figure) {
        currentLayer.addFigure(figure);
//        addFigureToLastLayerItemsList(figure);
        addFigureToCurrentLayerItemsList(figure);
    }

    public void removeFigure(int ind) {
        currentLayer.getLayer().remove(ind);
    }
    
    public boolean isEmpty() { //нет фигур вообще
        for (int i = 0; i < layers.size(); i++) {
            if(layers.get(i).getLayer().getObjectsCount() >= 0) {
                return false;
            }
        }
        return true;
    }

    public LayerBoxController getCurrentLayer() {
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
        currentLayer = controller;
    }
}
