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
        mainController.changeLayerBox(layerBox);
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
        LayerItemController currentLayer = layerBox.getCurrentLayer();
        currentLayer.addFigure(figure);
        //addFigureToCurrentLayerItemsList(figure);
    }

    public void addFigureToCurrentLayerItemsList(Figure figure) {
        LayerItemController currentLayer = layerBox.getCurrentLayer();
        currentLayer.addFigure(figure);
    }

    public void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (int i = 0; i < layerBox.getLayers().size(); i++) {
            LayerItemController layer = layerBox.getLayers().get(i);
            for (int j = 0; j < layer.getFiguresCount(); j++) {
                layer.getFigure(j).draw(drawCanvas.getGraphicsContext2D());
            }
        }

        currentFigure.ifPresent(figure -> bordersPainter.drawBoards(figure, layerBox.getCurrentLayer().getColor()));


//        currentFigure.ifPresent(bordersPainter::drawBoards);
    }


    public boolean isEmpty() {
        for (LayerItemController layer : layerBox.getLayers()) {
            if (layer.getFiguresCount() >= 0) {
                return false;
            }
        }
        return true;
    }

    public LayerBox getLayerBox() {
        return layerBox;
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
        bordersPainter.drawBoards(currentFigure.get(), layerBox.getCurrentLayer().getColor());
    }

    public void selectFigure(LayerItemController layerItemController, Figure figure) {
        currentFigure = Optional.of(figure);
        layerBox.setCurrentLayer(layerItemController);
    }

    public void removeFigureSelection() {
        currentFigure = Optional.empty();
    }

    public LayerItemController getCurrentLayer() {
        return layerBox.getCurrentLayer();
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

}
