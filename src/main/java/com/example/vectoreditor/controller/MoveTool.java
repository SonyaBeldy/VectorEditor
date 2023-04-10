package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.figures.Figure;
import com.example.vectoreditor.model.Point;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class MoveTool extends Tool implements ITool{

    private final Point pressPoint;
//    Figure beforeMoveFigure;
    List<Figure> beforeMoveFigures;

    public MoveTool(MainController mainController) {
        super(mainController);
        pressPoint = new Point(0,0);

    }

    public MoveTool(MainController mainController, Point pressPoint) {
        super(mainController);
        this.pressPoint = pressPoint;
        beforeMoveFigures = mainController.getCurrentCanvasController().getSelectedFiguresList().cloneFigures();
//        for (int i = 0; i < mainController.getCurrentCanvasController().getSelectedFiguresList().size(); i++) {
//            beforeMoveFigures.add(mainController.getCurrentCanvasController().getSelectedFiguresList().get(i).getFigure().clone());
//        }
////        beforeMoveFigure = mainController.getCurrentCanvasController().getCurrentFigureController().orElseThrow().getFigure().clone();
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if(mainController.getCurrentCanvasController().getSelectedFiguresList().isEmpty()) {
            return;
        }
        double shiftX =  event.getX() - pressPoint.getX();
        double shiftY =  event.getY() - pressPoint.getY();

//        mainController.getCurrentCanvasController().getCurrentFigureController().get().getFigure().move(new Point(shiftX, shiftY));
        mainController.getCurrentCanvasController().getSelectedFiguresList().move(new Point(shiftX, shiftY));
        mainController.getCurrentCanvasController().redrawAllFigures();

        pressPoint.setX(event.getX());
        pressPoint.setY(event.getY());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        CanvasViewController currentCanvasController = mainController.getCurrentCanvasController();
        currentCanvasController.redrawAllFigures();

        mainController.setCurrentTool(new SelectTool(mainController));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

}
