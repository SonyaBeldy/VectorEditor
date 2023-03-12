package com.example.vectoreditor.controller.figureTool;

import com.example.vectoreditor.controller.*;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.figure.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Optional;

public class RectangleTool extends Tool implements ITool {


    private Rectangle rectangle;
    public RectangleTool(MainController mainController) {
        super(mainController);
        rectangle = new Rectangle(Color.BLACK);
    }

    @Override
    public void mousePressed(MouseEvent event) {

        HashMap<Integer, Integer> map = new HashMap<>();
        rectangle = new Rectangle(mainController.getCurrentCanvasController().getStrokeColor());
        for (int i = 0; i < 4; i++) {
            rectangle.addPoint(new Point(event.getX(), event.getY()));
        }
        mainController.getCurrentCanvasController().addFigure(rectangle);

    }

    @Override
    public void mouseDragged(MouseEvent event) {

        rectangle.getPoints().get(1).setX(event.getX());

        rectangle.getPoints().get(2).setX(event.getX());
        rectangle.getPoints().get(2).setY(event.getY());

        rectangle.getPoints().get(3).setY(event.getY());

        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        rectangle.calcBoardsPoints();
        rectangle.calcCenter();
        mainController.getCurrentCanvasController().setCurrentFigure(Optional.of(rectangle));

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }
}
