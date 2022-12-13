package com.example.vectoreditor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    private Button lineButton;

    @FXML
    private Button selectButton;

    @FXML
    private Canvas drawCanvas;

    private ITool currentTool;

    private CanvasController canvasController;

    private final String toolSelectedStyle = "/com/example/vectoreditor/activeTool.css";
    private final String toolCasualStyle = "/com/example/vectoreditor/main.css";


    @FXML
    protected void onLineButtonClick(ActionEvent event) {
        currentTool = new LineTool(canvasController);
        //lineButton.getStylesheets().add(getClass().getResource(toolSelectedStyle).toExternalForm());
        //selectButton.getStylesheets().add(getClass().getResource(toolCasualStyle).toExternalForm());
        lineButton.setDisable(true);
        selectButton.setDisable(false);

    }

    @FXML
    protected void onSelectButtonClick(ActionEvent event) {
        currentTool = new SelectTool(canvasController);
        selectButton.setDisable(true);
        lineButton.setDisable(false);

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
    void initialize() {
        canvasController = new CanvasController(drawCanvas);
        currentTool = new SelectTool(canvasController);

    }

}