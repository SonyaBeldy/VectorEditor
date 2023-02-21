package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Optional;

public class RectangleTool extends Tool implements ITool {


    private Rectangle rectangle;
    public RectangleTool(CanvasController canvasController) {
        super(canvasController);
        rectangle = new Rectangle(Color.BLACK);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        rectangle = new Rectangle(canvasController.getStrokeColor());
        for (int i = 0; i < 4; i++) {
            rectangle.addPoint(new Point(event.getX(), event.getY()));
        }
        canvasController.getFigures().add(rectangle);

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        rectangle.getPoints().get(1).setX(event.getX());

        rectangle.getPoints().get(2).setX(event.getX());
        rectangle.getPoints().get(2).setY(event.getY());

        rectangle.getPoints().get(3).setY(event.getY());

        canvasController.redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        rectangle.calcBoardsPoints();
        rectangle.calcCenter();
        canvasController.setCurrentFigure(Optional.of(rectangle));

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }
}
