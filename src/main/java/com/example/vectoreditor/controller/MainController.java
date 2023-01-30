package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.IDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

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

    @FXML
    private TextField rotateField;

    private CanvasController canvasController;
    private CurrentFigureParamsDisplay paramsDisplay;
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
    void onCanvasPressed(MouseEvent event) {
        canvasController.getCurrentTool().mousePressed(event);
    }
    @FXML
    void onCanvasDragged(MouseEvent event) {
        canvasController.getCurrentTool().mouseDragged(event);
    }
    @FXML
    void onCanvasReleased(MouseEvent event) {
        canvasController.getCurrentTool().mouseReleased(event);
        paramsDisplay.update();
    }

    @FXML
    void onCanvasEntered(MouseEvent event) { canvasController.getCurrentTool().mouseEntered(event); }


    @FXML
    private void initialize() {
        canvasController = new CanvasController(drawCanvas);
        canvasController.setCurrentTool(new SelectTool(canvasController));
        paramsDisplay = new CurrentFigureParamsDisplay(this);
        selectButton.setDisable(true);
        colorPicker.setValue(Color.BLACK);
    }

    @FXML
    private void setAngle() {
        if(canvasController.getCurrentFigure() != null) {
            double angle = Math.toRadians(Double.parseDouble(rotateField.getText()));
            canvasController.getCurrentFigure().setAngle(angle);
            canvasController.getCurrentFigure().rotate(canvasController.getCurrentFigure().clone(), canvasController.getCurrentFigure().getCenter(), angle);
        }
    }


    private void enabledAllButtons(){
        selectButton.setDisable(false);
        lineButton.setDisable(false);
        polylineButton.setDisable(false);
    }


    public void setRotate(double angle){
        rotateField.setText(String.valueOf(angle));
    }
    public TextField getRotate() {
        return rotateField;
    }

    public CanvasController getCanvasController() {
        return canvasController;
    }
}