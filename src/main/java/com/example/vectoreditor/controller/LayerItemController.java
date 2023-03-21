package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Layer;
import com.example.vectoreditor.model.figure.Figure;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LayerItemController implements Initializable {


    //private Layer layer;
    private String name;

    private ArrayList<FigureItemController> figureControllers;
    private LayerBox layerBoxController;
    @FXML
    private HBox layerItem;

    @FXML
    private Rectangle layerColor;

    @FXML
    private TextField layerNameField;

    @FXML
    private VBox figuresBox;

    @FXML
    private ImageView visible;

    @FXML
    private ImageView showFiguresImg;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        layerNameField.setEditable(false);
        layerNameField.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                layerNameField.setEditable(true);
                layerNameField.setCursor(Cursor.TEXT);

            } else {
                layerItem.fireEvent(event);
            }
        });
    }

    public void removeFigure(int ind) {
//        layer.remove(ind);
        figureControllers.remove(ind);
    }
    public int getFiguresCount() {
        return figureControllers.size();
    }
    public String getName() {
        return layerNameField.getText();
    }


    public void init(LayerBox layerBoxController, String layerName, Color color) {
        this.layerBoxController = layerBoxController;
        //layer = new Layer(layerName);
        //setLayerName();
        layerNameField.setText(layerName);
        layerColor.setFill(color);
        figureControllers = new ArrayList<>();
        figuresBox.setVisible(false);
        figuresBox.setManaged(false);
    }

    @FXML
    void layerItemClick(MouseEvent event) {
        layerBoxController.setCurrentLayer(this);
        highlight();
    }

    public void highlight() {
        ObservableList<Node> childrenUnmodifiable = layerItem.getParent().getParent().getChildrenUnmodifiable();
        for (Node node : childrenUnmodifiable) {
            node.setStyle("-fx-background-color: transparent");
        }
        layerItem.getParent().setStyle("-fx-background-color: dodgerblue");
    }

    @FXML
    void layerNameAction(ActionEvent event) {
        layerNameField.setEditable(false);
        layerNameField.setCursor(Cursor.DEFAULT);
    }

    @FXML
    void showFiguresButton(ActionEvent event) {
        showFiguresImg.setRotate((showFiguresImg.getRotate() + 90) % 180);

        if (figuresBox.isVisible()) {
            figuresBox.setVisible(false);
            figuresBox.setManaged(false);
        } else {
            figuresBox.setVisible(true);
            figuresBox.setManaged(true);
        }
    }

    public HBox getLayerItem() {
        return layerItem;
    }

    public void addFigure(Figure figure) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/vectoreditor/figure_item.fxml"));
        HBox figureBox;
        try {
            figureBox = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FigureItemController figureItem = fxmlLoader.getController();
        figureItem.setFigure(figure);
        figureControllers.add(figureItem);
        figuresBox.getChildren().add(figureBox);
    }

    public void setLayerNameField(String name) {
        layerNameField.setText(name);
    }

    public Color getColor() {
        return (Color) layerColor.getFill();
    }

    public Figure getFigure(int ind) {
        return figureControllers.get(ind).getFigure();
    }
}
