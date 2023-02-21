package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class SelectTool extends Tool implements ITool {

    public SelectTool(CanvasController canvasController) {
        super(canvasController);
    }

    @Override
    public void mousePressed(MouseEvent event) {

        Optional<Figure> currentFigure = canvasController.whatWasClickedOn(event);
        if (currentFigure.isEmpty()) {
            return;
        }
        canvasController.setCurrentFigure(currentFigure);
        canvasController.redrawAllFigures();
        canvasController.setCurrentTool(new MoveTool(canvasController, new Point(event.getX(), event.getY())));
    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (canvasController.getCurrentFigure().isEmpty()) {
            return;
        }
        canvasController.redrawAllFigures();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (canvasController.getFigures().isEmpty()) {
            return;
        }
        canvasController.redrawAllFigures();
        Optional<Figure> enteredFigure = canvasController.whatWasClickedOn(event);
        enteredFigure.ifPresent(figure -> figure.highlight(drawCanvas.getGraphicsContext2D()));
        bordersControlsEntered(event);
    }

    private void bordersControlsEntered(MouseEvent event) {
        Point point = new Point(event.getX(), event.getY());
        Figure currentFigure = canvasController.getCurrentFigure().get();
        Cursor cursor = Cursor.DEFAULT;
        Point dragPoint = new Point(0,0);
        Point oppositePoint = new Point(0,0);
        for (int i = 0; i < currentFigure.getBoardsPoints().size(); i++) {
            Point boardCorner = currentFigure.getBoardsPoints().get(i);
            if (Math.pow(boardCorner.getX() - point.getX(), 2) + Math.pow(boardCorner.getY() - point.getY(), 2) <= 20) {
                System.out.println("IN");
                System.out.println(" i " + i);
                switch (i) {
                    case 0 -> {
                        System.out.println("0 change");
                        cursor = Cursor.NW_RESIZE;
                        dragPoint = currentFigure.getBoardsPoints().get(0);
                        oppositePoint = currentFigure.getBoardsPoints().get(2);
                    }
                    case 1 -> {
                        cursor = Cursor.NE_RESIZE;
                        dragPoint = currentFigure.getBoardsPoints().get(1);
                        oppositePoint = currentFigure.getBoardsPoints().get(3);
                    }
                    case 2 -> {
                        cursor = Cursor.SE_RESIZE;
                        dragPoint = currentFigure.getBoardsPoints().get(2);
                        oppositePoint = currentFigure.getBoardsPoints().get(0);
                    }
                    case 3 -> {
                        cursor = Cursor.SW_RESIZE;
                        dragPoint = currentFigure.getBoardsPoints().get(3);
                        oppositePoint = currentFigure.getBoardsPoints().get(1);
                    }
                }
                canvasController.getDrawCanvas().getScene().setCursor(cursor);
                canvasController.setCurrentTool(new ResizeTool(canvasController, dragPoint, oppositePoint, ResizeDirection.XY));
                return;
            }
        }
        canvasController.setCurrentTool(new SelectTool(canvasController));
        for (int i = 0; i < currentFigure.getResizePoints().size(); i++) {
            Point boardCorner = currentFigure.getResizePoints().get(i);
            if (Math.pow(boardCorner.getX() - point.getX(), 2) + Math.pow(boardCorner.getY() - point.getY(), 2) <= Values.RESIZE_HITBOX) {
                switch (i) {
                    case 0 -> {
                        cursor = Cursor.N_RESIZE;
                        dragPoint = currentFigure.getResizePoints().get(0);
                        oppositePoint = currentFigure.getResizePoints().get(2);
                        canvasController.setCurrentTool(new ResizeTool(canvasController, dragPoint, oppositePoint, ResizeDirection.Y));
                    }
                    case 1 -> {
                        cursor = Cursor.E_RESIZE;
                        dragPoint = currentFigure.getResizePoints().get(1);
                        oppositePoint = currentFigure.getResizePoints().get(3);
                        canvasController.setCurrentTool(new ResizeTool(canvasController, dragPoint, oppositePoint , ResizeDirection.X));
                    }
                    case 2 -> {
                        cursor = Cursor.S_RESIZE;
                        dragPoint = currentFigure.getResizePoints().get(2);
                        oppositePoint = currentFigure.getResizePoints().get(0);
                        canvasController.setCurrentTool(new ResizeTool(canvasController, dragPoint, oppositePoint, ResizeDirection.Y));
                    }
                    case 3 -> {
                        cursor = Cursor.W_RESIZE;
                        dragPoint = currentFigure.getResizePoints().get(3);
                        oppositePoint = currentFigure.getResizePoints().get(1);
                        canvasController.setCurrentTool(new ResizeTool(canvasController, dragPoint, oppositePoint, ResizeDirection.X));
                    }
                }
                canvasController.getDrawCanvas().getScene().setCursor(cursor);
                return;
            }
        }
        for (int i = 0; i < currentFigure.getRotatePoints().size(); i++) {
            Point rotatePoint = currentFigure.getRotatePoints().get(i);

            if (Math.pow(rotatePoint.getX() - point.getX(), 2) + Math.pow(rotatePoint.getY() - point.getY(), 2) <= 80) {

                final Scene scene = canvasController.getDrawCanvas().getScene();
                switch (i) {
                    case 0 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.NW, canvasController.getCurrentFigure().get().getAngle()));
                    case 1 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.SW, canvasController.getCurrentFigure().get().getAngle()));
                    case 2 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.SE, canvasController.getCurrentFigure().get().getAngle()));
                    case 3 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.NE, canvasController.getCurrentFigure().get().getAngle()));
                }
                canvasController.setCurrentTool(new RotateTool(canvasController));
                return;
            }
        }
        canvasController.setCurrentTool(new SelectTool(canvasController));
        canvasController.getDrawCanvas().getScene().setCursor(Cursor.DEFAULT);
    }
}
