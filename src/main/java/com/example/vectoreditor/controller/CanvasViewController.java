package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.BordersPainter;
import com.example.vectoreditor.model.Frame2;
import com.example.vectoreditor.model.figure.Figure;
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

    private VBox propertiesBox;

    private PropertiesBoxController propertiesBoxController;
    private Color strokeColor;
//    private BordersPainter bordersPainter;
    private FrameDrawer frameDrawer;
    private MainController mainController;

    @FXML
    private Canvas drawCanvas;

    public void init(MainController mainController) {
        this.mainController = mainController;
        layerBox = new LayerBox(mainController);
        strokeColor = Color.BLACK;
        frameDrawer = new FrameDrawer(drawCanvas.getGraphicsContext2D());
        //bordersPainter = new BordersPainter(drawCanvas.getGraphicsContext2D());
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

    public void addFigure(Figure figure) {
        LayerItemController currentLayerController = layerBox.getCurrentLayer();
        FigureItemController figureController = currentLayerController.addFigure(figure);
        figureController.init(mainController, currentLayerController);
        currentFigureController = Optional.of(figureController);

        //addFigureToCurrentLayerItemsList(figure);
    }

    public void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (int i = 0; i < layerBox.getLayers().size(); i++) {
            LayerItemController layer = layerBox.getLayers().get(i);
            for (int j = 0; j < layer.getFiguresCount(); j++) {
                layer.getFigureController(j).getFigure().draw(drawCanvas.getGraphicsContext2D());
            }
        }
//        currentFigureController.ifPresent(figureController -> bordersPainter.drawBoards(figureController.getFigure(), currentFigureController.get().getLayerController().getColor()));

        if (currentFigureController.isPresent()) {
            Frame2 frame2 = new Frame2(currentFigureController.orElseThrow().getFigure());

//            frameDrawer.draw(frame2, currentFigureController.get().getLayerController().getColor()); t
        }


//        currentFigureController.ifPresent(figureItemController -> frameDrawer.draw(figureItemController.getFigure().getFrame(), figureItemController.getLayerController().getColor()));
//
//        if(currentFigureController.isPresent()) {
//            frameDrawer.draw(currentFigureController.get().getFigure().getFrame(), currentFigureController.get().getLayerController().getColor());
//        }

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

    public Optional<FigureItemController> getCurrentFigureController() {
        return currentFigureController;
    }

    public void setCurrentFigureController(Optional<FigureItemController> currentFigureController) {
        this.currentFigureController = currentFigureController;
        if (currentFigureController.isEmpty()) {
            return;
        }
        redrawAllFigures();
//        bordersPainter.drawBoards(currentFigureController.get().getFigure(), layerBox.getCurrentLayer().getColor());
        Frame2 frame2 = new Frame2(currentFigureController.orElseThrow().getFigure());
//        frameDrawer.draw(frame2, layerBox.getCurrentLayer().getColor());
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

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

}
