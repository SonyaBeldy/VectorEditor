package com.example.vectoreditor.controller;

import com.example.vectoreditor.controller.figureTool.PolygonTool;
import com.example.vectoreditor.controller.figureTool.PolylineTool;
import com.example.vectoreditor.controller.figureTool.RectangleTool;
import com.example.vectoreditor.model.figure.Figure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
    private Button deleteLayerButton;

    @FXML
    private VBox layerBoxContainer;

    @FXML
    private TabPane workspaceTabPane;
    @FXML
    private ScrollPane propScrollPane;

    private CurrentFigureParamsDisplay paramsDisplay;
    private LayerBox layerBox;
    private CanvasViewController currentCanvasController;
    private ITool currentTool = new SelectTool(this);
    private PropertiesBoxController propertiesBoxController;
    private PropertiesBox propertiesBox;

    @FXML
    private void initialize() {
        //paramsDisplay = new CurrentFigureParamsDisplay(this);
        propertiesBox = new PropertiesBox();

        NewFileViewController newFileViewController = new NewFileViewController();
        newFileViewController.init(this);
        newFileViewController.createNewView();

        loadPropertiesPane();

        selectButton.setDisable(true);


        deleteLayerButton.setDisable(true);
    }

    public void swapLayerBox(LayerBox layerBox) {
        this.layerBox = layerBox;
        layerBoxContainer.getChildren().clear();
        layerBoxContainer.getChildren().add(layerBox);
        if (layerBox.getLayers().size() < 2) {
            deleteLayerButton.setDisable(true);
        }
    }

    public void updatePropertiesBox(Optional<FigureItemController> figureItemController) {
        propertiesBox.update(figureItemController);
    }

    private void loadPropertiesPane() {
        NodeController<PropertiesBoxController> propBox = MyFXMLLoader.loadPropertiesBox();
        propScrollPane.setContent(propBox.node);
        propertiesBoxController = propBox.controller;
        propBox.controller.init(currentCanvasController);
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
//        NodeController<CanvasViewController> canvasView = MyFXMLLoader.loadCanvasView();
//        Scene scene = new Scene((Parent) canvasView.node);
//        Stage stage = new Stage();
//        stage.setTitle("Create file");
//        stage.setScene(scene);
//
//        canvasView.controller.init(this);
//        stage.show();
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

        CanvasViewController canvasViewController = fxmlLoader.getController();
        canvasViewController.init(this);

        workspaceTabPane.getSelectionModel().select(newCanvasView);
        newCanvasView.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                currentCanvasController = canvasViewController;
                swapLayerBox(canvasViewController.getLayerBox());
                updatePropertiesBox(canvasViewController.getCurrentFigureController());
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
//        currentCanvasController.setStrokeColor(colorPicker.getValue());
        currentTool = new PolylineTool(this);
        enabledAllButtons();
        polylineButton.setDisable(true);
    }

    @FXML
    protected void onRectangleButtonClick(ActionEvent event) {
//        currentCanvasController.setStrokeColor(colorPicker.getValue());
        currentTool = new RectangleTool(this);
        enabledAllButtons();
        rectangleButton.setDisable(true);
    }

    @FXML
    protected void onPolygonButtonClick(ActionEvent event) {
//        currentCanvasController.setStrokeColor(colorPicker.getValue());
        currentTool = new PolygonTool(this);
        enabledAllButtons();
        polygonButton.setDisable(true);
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

    public VBox getPropertiesBox() {
        return propertiesBox;
    }

    public PropertiesBoxController getPropertiesBoxController() {
        return propertiesBoxController;
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

}