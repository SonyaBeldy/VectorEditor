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
    private Button lineButton;

    @FXML
    private Button selectButton;

    @FXML
    private Canvas drawCanvas;

    @FXML
    private VBox vBoxProperties;

    @FXML
    private ColorPicker colorPicker;

    private ITool currentTool;

    private CanvasController canvasController;

    @FXML
    protected void onLineButtonClick(ActionEvent event) {
        canvasController.setFillColor(colorPicker.getValue());
        currentTool = new LineTool(canvasController);
        enabledAllButtons();
        lineButton.setDisable(true);
    }

    @FXML
    protected void onSelectButtonClick(ActionEvent event) {
        currentTool = new SelectTool(canvasController);
        enabledAllButtons();
        selectButton.setDisable(true);
    }

    @FXML
    protected void chooseColor() {
        canvasController.setFillColor(colorPicker.getValue());
    }

    @FXML
    void onCanvasClick(MouseEvent event){
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
        lineButton.setDisable(false);
        selectButton.setDisable(false);
    }

}