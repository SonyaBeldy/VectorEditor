package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.figure.Polygon;
import javafx.scene.input.MouseEvent;

public class SelectionFrameTool extends Tool implements ITool {

    private final SelectionFrame selectionFrame;
    private final Point pressPoint;
    public SelectionFrameTool(MainController mainController, Point pressPoint) {
        super(mainController);
        selectionFrame = new SelectionFrame(mainController.getCurrentCanvasController().getDrawCanvas().getGraphicsContext2D());
        this.pressPoint = pressPoint;
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        selectionFrame.draw(pressPoint, new Point(event.getX(), event.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        mainController.setCurrentTool(new SelectTool(mainController));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }
}
