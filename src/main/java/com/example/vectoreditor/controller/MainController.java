package com.example.vectoreditor.controller;

import com.example.vectoreditor.controller.figureTool.PolygonTool;
import com.example.vectoreditor.controller.figureTool.PolylineTool;
import com.example.vectoreditor.controller.figureTool.RectangleTool;
import com.example.vectoreditor.model.figure.Figure;
import com.example.vectoreditor.model.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

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
    private Button deleteLayerButton;

    @FXML
    private VBox layerBoxContainer;

    @FXML
    private TabPane workspaceTabPane;

    private CurrentFigureParamsDisplay paramsDisplay;
    private LayerBox layerBox;
    private CanvasViewController currentCanvasController;
    private ITool currentTool = new SelectTool(this);

    @FXML
    private void initialize() {
        paramsDisplay = new CurrentFigureParamsDisplay(this);

        NewFileViewController newFileViewController = new NewFileViewController();
        newFileViewController.init(this);
        newFileViewController.createNewView();

        selectButton.setDisable(true);
        colorPicker.setValue(Color.BLACK);

        deleteLayerButton.setDisable(true);
    }

    public void changeLayerBox(LayerBox layerBox) {
        this.layerBox = layerBox;
        layerBoxContainer.getChildren().clear();
        layerBoxContainer.getChildren().add(layerBox);
        paramsDisplay.update(currentCanvasController);
        if (layerBox.getLayers().size() < 2) {
            deleteLayerButton.setDisable(true);
        }
    }

    @FXML
    protected void newFileMenuItemClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/vectoreditor/new-file-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Create file");
            stage.setScene(scene);

            NewFileViewController newFileViewController = fxmlLoader.getController();
            newFileViewController.init(this);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewCanvasView() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/vectoreditor/canvas-view.fxml"));
        ScrollPane scrollPane;
        try {
            scrollPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Tab newCanvasView = createTab(scrollPane);
        workspaceTabPane.getTabs().add(newCanvasView);

        CanvasViewController scrollPaneController = fxmlLoader.getController();
        scrollPaneController.init(this);

        workspaceTabPane.getSelectionModel().select(newCanvasView);
        newCanvasView.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                currentCanvasController = scrollPaneController;
                changeLayerBox(scrollPaneController.getLayerBox());
            }
        });
    }

    @FXML
    protected void addNewLayerButtonClick() {
        deleteLayerButton.setDisable(false);
        layerBox.createLayer();
    }

    @FXML
    protected void deleteLayerButtonClick() {
        if (layerBox.canDeleteLayer()) {
            layerBox.deleteCurrentLayer();
        }
    }
    @FXML
    protected void onSelectButtonClick(ActionEvent event) {
        currentTool = new SelectTool(this);
        enabledAllButtons();
        selectButton.setDisable(true);
    }

    @FXML
    protected void onLineButtonClick(ActionEvent event) {

    }

    @FXML
    protected void onPolylineButtonClick(ActionEvent event) {
        currentCanvasController.setStrokeColor(colorPicker.getValue());
        currentTool = new PolylineTool(this);
        enabledAllButtons();
        polylineButton.setDisable(true);
    }

    @FXML
    protected void onRectangleButtonClick(ActionEvent event) {
        currentCanvasController.setStrokeColor(colorPicker.getValue());
        currentTool = new RectangleTool(this);
        enabledAllButtons();
        rectangleButton.setDisable(true);
    }

    @FXML
    protected void onPolygonButtonClick(ActionEvent event) {
        currentCanvasController.setStrokeColor(colorPicker.getValue());
        currentTool = new PolygonTool(this);
        enabledAllButtons();
        polygonButton.setDisable(true);
    }

    @FXML
    protected void chooseColor() {
        currentCanvasController.setStrokeColor(colorPicker.getValue());
    }

    @FXML
    private void move() {
        if(currentCanvasController.getCurrentFigure().isEmpty()) {
            return;
        }
        Figure figure = currentCanvasController.getCurrentFigure().get();

        Point newCenter = new Point(Double.parseDouble(xPointField.getText()), Double.parseDouble(yPointField.getText()));
        Point center = figure.getCenter();
        Point shift = new Point(newCenter.getX() - center.getX(), newCenter.getY() - center.getY());
        figure.move(shift);
        currentCanvasController.redrawAllFigures();
    }

    @FXML
    private void rotate() {
        if(currentCanvasController.getCurrentFigure().isEmpty()) {
            return;
        }
        double angle = Double.parseDouble(rotateField.getText());
        angle = Math.toRadians(angle);
        Figure figure = currentCanvasController.getCurrentFigure().get();

        double angleDiff = angle - figure.getAngle();
        figure.rotate(figure, figure.getCenter(), angleDiff);
        figure.setAngle(angle);
        currentCanvasController.redrawAllFigures();
    }

    private Tab createTab(Node content) {
        Tab newCanvasView = new Tab();
        newCanvasView.setContent(content);
        newCanvasView.setText("Untitled " + workspaceTabPane.getTabs().size());
        return newCanvasView;
    }

    private void enabledAllButtons(){
        selectButton.setDisable(false);
        lineButton.setDisable(false);
        polylineButton.setDisable(false);
        rectangleButton.setDisable(false);
        polygonButton.setDisable(false);
    }

    public CanvasViewController getCurrentCanvasController() {
        return currentCanvasController;
    }

    public void setCurrentCanvasController(CanvasViewController currentCanvasController) {
        this.currentCanvasController = currentCanvasController;
    }

    public ITool getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(ITool currentTool) {
        this.currentTool = currentTool;
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

}