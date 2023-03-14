package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.BordersPainter;
import com.example.vectoreditor.model.Layer;
import com.example.vectoreditor.model.figure.Figure;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Optional;

public class CanvasViewController extends ScrollPane {

    private Optional<Figure> currentFigure = Optional.empty();
    private final LayerBox layerBox = new LayerBox();
    private Color strokeColor;
    private BordersPainter bordersPainter;
    private MainController mainController;


    @FXML
    private Canvas drawCanvas;

    public void init(MainController mainController) {
        this.mainController = mainController;
        strokeColor = Color.BLACK;
        bordersPainter = new BordersPainter(drawCanvas.getGraphicsContext2D());
        mainController.setCurrentCanvasController(this);
        layerBox.createLayer();
        mainController.setLayerBox(layerBox);
    }

    @FXML
    private void onCanvasMouseMoved(MouseEvent event) {
        mainController.getCurrentTool().mouseEntered(event);
    }
    @FXML
    private void onCanvasMousePressed(MouseEvent event) {
        mainController.getCurrentTool().mousePressed(event);
    }
    @FXML
    private void onCanvasMouseDragged(MouseEvent event) {
        mainController.getCurrentTool().mouseDragged(event);
        mainController.changeLayerBox(layerBox);
    }
    @FXML
    private void onCanvasMouseReleased(MouseEvent event) {
        mainController.getCurrentTool().mouseReleased(event);
        mainController.changeLayerBox(layerBox);
    }

    public void addFigure(Figure figure) {
        Optional<LayerItemController> currentLayer = layerBox.getCurrentLayer();
        if (currentLayer.isPresent()) {
            currentLayer.get().addFigure(figure);
            addFigureToCurrentLayerItemsList(figure);
        }
    }

    public void addFigureToCurrentLayerItemsList(Figure figure) {
        Optional<LayerItemController> currentLayer = layerBox.getCurrentLayer();
        if (currentLayer.isPresent()) {
            currentLayer.get().getLayer().addFigure(figure);
        }
    }

    public void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (int i = 0; i < layerBox.getLayers().size(); i++) {
            Layer layer = layerBox.getLayers().get(i).getLayer();
            for (int j = 0; j < layer.getObjectsCount(); j++) {
                layer.getFigure(j).draw(drawCanvas.getGraphicsContext2D());
            }
        }
        currentFigure.ifPresent(bordersPainter::drawBoards);
    }

    public Optional<Figure> whatWasClickedOn(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        for (int i = layerBox.getLayers().size() - 1; i >= 0; i--) {
            Layer layer = layerBox.getLayers().get(i).getLayer();
            for (int j = 0; j < layer.getObjectsCount(); j++) {
                Figure figure = layer.getFigure(j);
                if(figure.isClickedOn(x, y)){
                    return Optional.of(figure);
                }
            }
        }
        return Optional.empty();
    }

    public boolean isEmpty() {
        for (LayerItemController layer : layerBox.getLayers()) {
            if (layer.getLayer().getObjectsCount() >= 0) {
                return false;
            }
        }
        return true;
    }

    public LayerBox getLayerBox() {
        return layerBox;
    }

    public ITool getCurrentTool() {
        return mainController.getCurrentTool();
    }

    public Canvas getDrawCanvas() {
        return drawCanvas;
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

    public Optional<LayerItemController> getCurrentLayer() {
        return layerBox.getCurrentLayer();
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

}
