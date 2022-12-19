package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.IDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainController {


    @FXML
    private Button selectButton;
    @FXML
    private Button lineButton;

    @FXML
    private Button polylineButton;

    @FXML
    private Canvas drawCanvas;

    @FXML
    private VBox vBoxProperties;

    @FXML
    private ColorPicker colorPicker;

    private CanvasController canvasController;

    @FXML
    protected void onSelectButtonClick(ActionEvent event) {
        canvasController.setCurrentTool(new SelectTool(canvasController));
        enabledAllButtons();
        selectButton.setDisable(true);
    }

    @FXML
    protected void onLineButtonClick(ActionEvent event) {

    }

    @FXML
    protected void onPolylineButtonClick(ActionEvent event) {
        canvasController.setStrokeColor(colorPicker.getValue());
        canvasController.setCurrentTool(new PolylineTool(canvasController));
        enabledAllButtons();
        polylineButton.setDisable(true);
    }



    @FXML
    protected void chooseColor() {
        canvasController.setStrokeColor(colorPicker.getValue());
    }

    @FXML
    void onCanvasPressed(MouseEvent event){
        canvasController.getCurrentTool().mousePressed(event);
    }
    @FXML
    void onCanvasDragged(MouseEvent event) {
        canvasController.getCurrentTool().mouseDragged(event);
    }
    @FXML
    void onCanvasReleased(MouseEvent event){
        canvasController.getCurrentTool().mouseReleased(event);
    }

    @FXML
    void onCanvasEntered(MouseEvent event) { canvasController.getCurrentTool().mouseEntered(event); }


    @FXML
    void initialize() {
        canvasController = new CanvasController(drawCanvas);
        canvasController.setCurrentTool(new SelectTool(canvasController));
        selectButton.setDisable(true);
        colorPicker.setValue(Color.BLACK);
    }


    void enabledAllButtons(){
        selectButton.setDisable(false);
        lineButton.setDisable(false);
        polylineButton.setDisable(false);
    }

}