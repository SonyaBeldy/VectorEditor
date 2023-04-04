package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Frame;
import com.example.vectoreditor.model.figures.Figure;
import com.example.vectoreditor.model.unused.FrameDrawer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Optional;

public class CanvasViewController extends ScrollPane {

    private Optional<FigureItemController> currentFigureController = Optional.empty();
    private LayerBox layerBox;

    private FrameDrawer frameDrawer;
    private MainController mainController;

    @FXML
    private Canvas drawCanvas;

    public void init(MainController mainController) {
        this.mainController = mainController;
        layerBox = new LayerBox(mainController);
        frameDrawer = new FrameDrawer(drawCanvas.getGraphicsContext2D());
        mainController.setCurrentCanvasController(this);
        layerBox.createLayer();
        mainController.swapLayerBox(layerBox);
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
        mainController.swapLayerBox(layerBox);
    }
    @FXML
    private void onCanvasMouseReleased(MouseEvent event) {
        mainController.getCurrentTool().mouseReleased(event);
        mainController.swapLayerBox(layerBox);
    }

    private Frame createFrame(Figure figure) {
        return new Frame(figure);
    }
    public void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (int i = 0; i < layerBox.getLayers().size(); i++) {
            LayerItemController layer = layerBox.getLayers().get(i);
            for (int j = 0; j < layer.getFiguresCount(); j++) {
                layer.getFigureController(j).getFigure().draw(drawCanvas.getGraphicsContext2D());
            }
        }
        currentFigureController.ifPresent(figureController -> frameDrawer.draw(createFrame(figureController.getFigure()), figureController.getLayerController().getColor()));
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

    public Optional<FigureItemController> getCurrentFigureController() {
        return currentFigureController;
    }

    public void setCurrentFigureController(Optional<FigureItemController> currentFigureController) {
        this.currentFigureController = currentFigureController;
        if (currentFigureController.isEmpty()) {
            return;
        }
        redrawAllFigures();
    }

    public void selectFigure(LayerItemController layerItemController, FigureItemController figureController) {
        currentFigureController = Optional.of(figureController);
        layerBox.setCurrentLayer(layerItemController);
    }

    public void removeFigureSelection() {
        currentFigureController = Optional.empty();
    }

    public LayerItemController getCurrentLayer() {
        return layerBox.getCurrentLayer();
    }

}
