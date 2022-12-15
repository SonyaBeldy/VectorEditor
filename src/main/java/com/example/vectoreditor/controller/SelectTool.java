package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.Point;
import javafx.scene.input.MouseEvent;

public class SelectTool extends Tool implements ITool{

    private final Point clickPoint;

    private Figure beforeDragFigure;

    public SelectTool(CanvasController canvasController){
        super(canvasController);
        clickPoint = new Point(0,0);
        beforeDragFigure = new Figure();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        currentFigure = canvasController.whatWasClickedOn(event);

        if (currentFigure == null) {
            return;
        }
        Point pStart = currentFigure.getStartPoint();
        Point pEnd = currentFigure.getEndPoint();
        beforeDragFigure.setStartPoint(pStart);
        beforeDragFigure.setEndPoint(pEnd);

        canvasController.redrawAllFigures();
        currentFigure.drawHitbox(drawCanvas.getGraphicsContext2D());
        clickPoint.setX(event.getX());
        clickPoint.setY(event.getY());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (currentFigure == null) {
            return;
        }
        double differenceX = event.getX() - clickPoint.getX();
        double differenceY = event.getY() - clickPoint.getY();

        Point s = new Point(beforeDragFigure.getStartPoint().getX() + differenceX, beforeDragFigure.getStartPoint().getY() + differenceY);
        Point e = new Point(beforeDragFigure.getEndPoint().getX() + differenceX, beforeDragFigure.getEndPoint().getY() + differenceY);

        currentFigure.setStartPoint(s);
        currentFigure.setEndPoint(e);

        canvasController.redrawFiguresWithoutCurrant();
        currentFigure.drawHitbox(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (currentFigure == null) {
            return;
        }
        double differenceX = event.getX() - clickPoint.getX();
        double differenceY = event.getY() - clickPoint.getY();

        Point s = new Point(beforeDragFigure.getStartPoint().getX() + differenceX, beforeDragFigure.getStartPoint().getY() + differenceY);
        Point e = new Point(beforeDragFigure.getEndPoint().getX() + differenceX, beforeDragFigure.getEndPoint().getY() + differenceY);

        currentFigure.setStartPoint(s);
        currentFigure.setEndPoint(e);
        canvasController.redrawAllFigures();
        currentFigure.drawHitbox(drawCanvas.getGraphicsContext2D());
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if(canvasController.getFigures().size() == 0) {
            return;
        }
        Figure enteredFigure = canvasController.whatWasClickedOn(event);
        if(enteredFigure == null) {
            return;
        }
        canvasController.redrawAllFigures();
        enteredFigure.drawHitbox(drawCanvas.getGraphicsContext2D());
    }
}
