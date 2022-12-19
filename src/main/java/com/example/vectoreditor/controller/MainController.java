package com.example.vectoreditor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
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

    private ITool currentTool;

    private CanvasController canvasController;

    @FXML
    protected void onSelectButtonClick(ActionEvent event) {
        currentTool = new SelectTool(canvasController);
        enabledAllButtons();
        selectButton.setDisable(true);
    }

    @FXML
    protected void onLineButtonClick(ActionEvent event) {
        canvasController.setStrokeColor(colorPicker.getValue());
        currentTool = new LineTool(canvasController);
        enabledAllButtons();
        lineButton.setDisable(true);
    }

    @FXML
    protected void onPolylineButtonClick(ActionEvent event) {
        canvasController.setStrokeColor(colorPicker.getValue());
        currentTool = new PolylineTool(canvasController);
        enabledAllButtons();
        polylineButton.setDisable(true);
    }



    @FXML
    protected void chooseColor() {
        canvasController.setStrokeColor(colorPicker.getValue());
    }

    @FXML
    void onCanvasPressed(MouseEvent event){
        currentTool.mousePressed(event);
    }
    @FXML
    void onCanvasDragged(MouseEvent event) {
        currentTool.mouseDragged(event);
    }
    @FXML
    void onCanvasReleased(MouseEvent event){
        currentTool.mouseReleased(event);
    }

    @FXML
    void onCanvasEntered(MouseEvent event) { currentTool.mouseEntered(event); }


    @FXML
    void initialize() {
        canvasController = new CanvasController(drawCanvas);
        currentTool = new SelectTool(canvasController);
        selectButton.setDisable(true);
        colorPicker.setValue(Color.BLACK);
    }


    void enabledAllButtons(){
        selectButton.setDisable(false);
        lineButton.setDisable(false);
        polylineButton.setDisable(false);
    }

}