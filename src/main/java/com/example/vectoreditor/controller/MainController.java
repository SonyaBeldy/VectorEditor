package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.IDrawer;
import com.example.vectoreditor.model.Point;
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
    private ColorPicker colorPicker;

    @FXML
    private TextField rotateField;
    @FXML
    private TextField xPointField;
    @FXML
    private TextField yPointField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField heightField;

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
        paramsDisplay.update();
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
        paramsDisplay = new CurrentFigureParamsDisplay(xPointField, yPointField, widthField, heightField, rotateField, canvasController.getCurrentFigure());
        selectButton.setDisable(true);
        colorPicker.setValue(Color.BLACK);
    }

    private void enabledAllButtons(){
        selectButton.setDisable(false);
        lineButton.setDisable(false);
        polylineButton.setDisable(false);
    }

    @FXML
    private void setAngle() {

        if(canvasController.getCurrentFigure() != null) {

            double angle = Double.parseDouble(rotateField.getText());
            angle = Math.toRadians(angle);
            double angleDiff = angle - canvasController.getCurrentFigure().getAngle();

//            if (angleDiff > Math.PI) {
//                angleDiff = -2 * Math.PI + angleDiff;
//            }
//            if (angleDiff < -Math.PI) {
//                angleDiff = 2 * Math.PI + angleDiff;
//            }
            canvasController.getCurrentFigure().rotate(canvasController.getCurrentFigure(), canvasController.getCurrentFigure().getCenter(), angleDiff);
            canvasController.redrawAllFigures();
            canvasController.getCurrentFigure().setAngle(angle);
        }
    }


    public TextField getRotate() {
        return rotateField;
    }

    public CanvasController getCanvasController() {
        return canvasController;
    }
}