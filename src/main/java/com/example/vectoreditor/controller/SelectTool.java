package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class SelectTool extends Tool implements ITool{

    private final Point clickPoint;

    public SelectTool(CanvasController canvasController){
        super(canvasController);
        clickPoint = new Point(0,0);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        currentFigure = canvasController.whatWasClickedOn(event);

        if (currentFigure == null) {
            return;
        }

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

        Point s = new Point(currentFigure.getStartPoint().getX() + differenceX, currentFigure.getStartPoint().getY() + differenceY);
        Point e = new Point(currentFigure.getEndPoint().getX() + differenceX, currentFigure.getEndPoint().getY() + differenceY);

        canvasController.redrawFiguresWithoutCurrant();
        currentFigure.draw(drawCanvas.getGraphicsContext2D(), s, e);
        currentFigure.drawHitbox(drawCanvas.getGraphicsContext2D(), s, e);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (currentFigure == null) {
            return;
        }
        double differenceX = event.getX() - clickPoint.getX();
        double differenceY = event.getY() - clickPoint.getY();

        Point s = new Point(currentFigure.getStartPoint().getX() + differenceX, currentFigure.getStartPoint().getY() + differenceY);
        Point e = new Point(currentFigure.getEndPoint().getX() + differenceX, currentFigure.getEndPoint().getY() + differenceY);

        currentFigure.setStartPoint(s);
        currentFigure.setEndPoint(e);
        canvasController.redrawAllFigures();
        currentFigure.draw(drawCanvas.getGraphicsContext2D());
        currentFigure.drawHitbox(drawCanvas.getGraphicsContext2D());
    }
}
