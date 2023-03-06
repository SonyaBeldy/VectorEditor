package com.example.vectoreditor.controller;

import com.example.vectoreditor.controller.figureTool.PolygonTool;
import com.example.vectoreditor.controller.figureTool.PolylineTool;
import com.example.vectoreditor.controller.figureTool.RectangleTool;
import com.example.vectoreditor.model.Layer;
import com.example.vectoreditor.model.figure.Figure;
import com.example.vectoreditor.model.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MainController {

    @FXML
    private Button addNewLayerButton;
    @FXML
    private Button showFigures;
    @FXML
    private VBox layersBox;
    @FXML
    private Button selectButton;
    @FXML
    private Button lineButton;
    @FXML
    private Button polylineButton;
    @FXML
    private Button rectangleButton;
    @FXML
    private Button polygonButton;
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

    @FXML
    private TextField layerName;

    private CanvasController canvasController;
    private CurrentFigureParamsDisplay paramsDisplay;


    @FXML
    private void initialize() {
        canvasController = new CanvasController(drawCanvas);
        canvasController.setCurrentTool(new SelectTool(canvasController));
//        paramsDisplay = new CurrentFigureParamsDisplay(xPointField, yPointField, widthField, heightField, rotateField, canvasController.getCurrentFigure());
        paramsDisplay = new CurrentFigureParamsDisplay(this);
        selectButton.setDisable(true);
        colorPicker.setValue(Color.BLACK);
    }

    @FXML
    protected void addNewLayerButtonClick() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/vectoreditor/layer_item.fxml"));
        try {
            HBox hBox = fxmlLoader.load();
            LayerItemController layerItem = fxmlLoader.getController();
            layersBox.getChildren().add(hBox);

            canvasController.addNewLayer();
            Layer layer = canvasController.getNewLayer().orElseThrow();
            layerItem.setLayer(layer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void showFiguresButton() {
        VBox vBox = new VBox();
    }
    @FXML
    protected void layerNameAction(ActionEvent event) {
        layerName.setEditable(false);
    }

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
    protected void onRectangleButtonClick(ActionEvent event) {
        canvasController.setStrokeColor(colorPicker.getValue());
        canvasController.setCurrentTool(new RectangleTool(canvasController));
        enabledAllButtons();
        rectangleButton.setDisable(true);
    }

    @FXML
    protected void onPolygonButtonClick(ActionEvent event) {
        canvasController.setStrokeColor(colorPicker.getValue());
        canvasController.setCurrentTool(new PolygonTool(canvasController));
        enabledAllButtons();
        polygonButton.setDisable(true);
    }

    private void figureButtonClick() {

    }

    @FXML
    protected void chooseColor() {
        canvasController.setStrokeColor(colorPicker.getValue());
    }
    @FXML
    void onCanvasPressed(MouseEvent event) {
        canvasController.getCurrentTool().mousePressed(event);
        paramsDisplay.update();
    }
    @FXML
    void onCanvasDragged(MouseEvent event) {
        canvasController.getCurrentTool().mouseDragged(event);
        paramsDisplay.update();
    }

    @FXML
    void onCanvasReleased(MouseEvent event) {
        canvasController.getCurrentTool().mouseReleased(event);
    }

    @FXML
    void onCanvasEntered(MouseEvent event) { canvasController.getCurrentTool().mouseEntered(event); }


    private void enabledAllButtons(){
        selectButton.setDisable(false);
        lineButton.setDisable(false);
        polylineButton.setDisable(false);
        rectangleButton.setDisable(false);
        polygonButton.setDisable(false);
    }

    @FXML
    private void move() {
        if(canvasController.getCurrentFigure().isEmpty()) {
            return;
        }
        Figure figure = canvasController.getCurrentFigure().get();

        Point newCenter = new Point(Double.parseDouble(xPointField.getText()), Double.parseDouble(yPointField.getText()));
        Point center = figure.getCenter();
        Point shift = new Point(newCenter.getX() - center.getX(), newCenter.getY() - center.getY());
        figure.move(shift);
        canvasController.redrawAllFigures();
    }

    @FXML
    private void rotate() {
        if(canvasController.getCurrentFigure().isEmpty()) {
            return;
        }
        double angle = Double.parseDouble(rotateField.getText());
        angle = Math.toRadians(angle);
        Figure figure = canvasController.getCurrentFigure().get();

        double angleDiff = angle - figure.getAngle();
        figure.rotate(figure, figure.getCenter(), angleDiff);
        figure.setAngle(angle);
        canvasController.redrawAllFigures();

    }

    public VBox getLayersBox() {
        return layersBox;
    }

    public TextField getXPointField() {
        return xPointField;
    }

    public TextField getYPointField() {
        return yPointField;
    }

    public TextField getWidthField() {
        return widthField;
    }

    public TextField getHeightField() {
        return heightField;
    }

    public TextField getRotate() {
        return rotateField;
    }

    public CanvasController getCanvasController() {
        return canvasController;
    }

}