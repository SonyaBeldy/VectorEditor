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


    @FXML
    protected void onLineButtonClick(ActionEvent event) {
        currentTool = new LineTool(canvasController);
    }

    @FXML
    protected void onSelectButtonClick(ActionEvent event) {
        currentTool = new SelectTool(canvasController);
    }

    @FXML
    protected void onButtonMouseReleased(MouseEvent event) {


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