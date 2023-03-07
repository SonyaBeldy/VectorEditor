package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Layer;
import com.example.vectoreditor.model.figure.Figure;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class LayerBoxController implements Initializable {


    Layer layer;
    @FXML
    private HBox layerItem;

    private boolean showLayerFiguresItem = false;

    @FXML
    private Rectangle color;

    @FXML
    private TextField layerName;

    @FXML
    private VBox figuresBox;

    @FXML
    private Button showFigures;

    @FXML
    private ImageView visible;

    CanvasController canvasController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeColor();
        layerName.setEditable(false);
        layerName.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                layerName.setEditable(true);
            } else {
                layerItem.fireEvent(event);
            }
        });
    }

    public Layer getLayer() {
        return layer;
    }

    public void init(CanvasController canvasController, String layerName) {
        layer = new Layer(layerName);
        setCanvasController(canvasController);
        setLayer();
    }

    public void setCanvasController(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @FXML
    void layerItemClick(MouseEvent event) {
        canvasController.setCurrentLayer(this);
        setCurrent();
    }

    public void setCurrent() {
        ObservableList<Node> childrenUnmodifiable = layerItem.getParent().getParent().getChildrenUnmodifiable();
        for (Node node : childrenUnmodifiable) {
            node.setStyle("-fx-background-color: transparent");
        }
        layerItem.getParent().setStyle("-fx-background-color: dodgerblue");
    }

    @FXML
    void layerNameAction(ActionEvent event) {
        layerName.setEditable(false);
    }

    @FXML
    void showFiguresButton(ActionEvent event) {
        if (!showLayerFiguresItem) {
            figuresBox.setVisible(true);
            figuresBox.setManaged(true);

            showLayerFiguresItem = true;
        } else {
            figuresBox.setVisible(false);
            figuresBox.setManaged(false);
            showLayerFiguresItem = false;
        }
    }

    public void addFigure(Figure figure) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/vectoreditor/figure_item.fxml"));
        try {
            HBox hBox = fxmlLoader.load();
            FigureItemController figureItem = fxmlLoader.getController();
            figureItem.setFigure(figure);
            figuresBox.getChildren().add(hBox);
            showLayerFiguresItem = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLayer() {
        layerName.setText(layer.getName());
    }

    public void setLayerName(String name) {
        layerName.setText(name);
    }

    public void changeColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        color.setFill(Color.color(r, g, b));
    }
}
