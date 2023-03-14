package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.*;
import com.example.vectoreditor.model.figure.Figure;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class SelectTool extends Tool implements ITool {

    public SelectTool(MainController mainController) {
        super(mainController);
    }

    @Override
    public void mousePressed(MouseEvent event) {

        Optional<Figure> currentFigure = mainController.getCurrentCanvasController().whatWasClickedOn(event);
        if (currentFigure.isEmpty()) {
            return; 
        }
        mainController.getCurrentCanvasController().setCurrentFigure(currentFigure);
        mainController.getCurrentCanvasController().redrawAllFigures();
        mainController.setCurrentTool(new MoveTool(mainController, new Point(event.getX(), event.getY())));
    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (mainController.getCurrentCanvasController().getCurrentFigure().isEmpty()) {
            return;
        }
        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (mainController.getCurrentCanvasController().isEmpty()) {
            return;
        }
        mainController.getCurrentCanvasController().redrawAllFigures();
        Optional<Figure> enteredFigure = mainController.getCurrentCanvasController().whatWasClickedOn(event);
        enteredFigure.ifPresent(figure -> figure.highlight(mainController.getCurrentCanvasController().getDrawCanvas().getGraphicsContext2D()));
        bordersControlsEntered(event);
    }

    private void bordersControlsEntered(MouseEvent event) {
        CanvasViewController currentCanvasController = mainController.getCurrentCanvasController();
        Point point = new Point(event.getX(), event.getY());
        if (currentCanvasController.getCurrentFigure().isEmpty()) {
            return;
        }
        Figure currentFigure = currentCanvasController.getCurrentFigure().get();
        Cursor cursor = Cursor.DEFAULT;
        Point dragPoint = new Point(0,0);
        Point oppositePoint = new Point(0,0);
        for (int i = 0; i < currentFigure.getBoardsPoints().size(); i++) {
            Point boardCorner = currentFigure.getBoardsPoints().get(i);
            if (Math.pow(boardCorner.getX() - point.getX(), 2) + Math.pow(boardCorner.getY() - point.getY(), 2) <= 20) {
                switch (i) {
                    case 0 -> {
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
                currentCanvasController.getDrawCanvas().getScene().setCursor(cursor);
                mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.XY));
                return;
            }
        }
        mainController.setCurrentTool(new SelectTool(mainController));
        for (int i = 0; i < currentFigure.getResizePoints().size(); i++) {
            Point boardCorner = currentFigure.getResizePoints().get(i);
            if (Math.pow(boardCorner.getX() - point.getX(), 2) + Math.pow(boardCorner.getY() - point.getY(), 2) <= Values.RESIZE_HITBOX) {
                switch (i) {
                    case 0 -> {
                        cursor = Cursor.N_RESIZE;
                        dragPoint = currentFigure.getResizePoints().get(0);
                        oppositePoint = currentFigure.getResizePoints().get(2);
                        mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.Y));
                    }
                    case 1 -> {
                        cursor = Cursor.E_RESIZE;
                        dragPoint = currentFigure.getResizePoints().get(1);
                        oppositePoint = currentFigure.getResizePoints().get(3);
                        mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint , ResizeDirection.X));
                    }
                    case 2 -> {
                        cursor = Cursor.S_RESIZE;
                        dragPoint = currentFigure.getResizePoints().get(2);
                        oppositePoint = currentFigure.getResizePoints().get(0);
                        mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.Y));
                    }
                    case 3 -> {
                        cursor = Cursor.W_RESIZE;
                        dragPoint = currentFigure.getResizePoints().get(3);
                        oppositePoint = currentFigure.getResizePoints().get(1);
                        mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.X));
                    }
                }
                currentCanvasController.getDrawCanvas().getScene().setCursor(cursor);
                return;
            }
        }
        for (int i = 0; i < currentFigure.getRotatePoints().size(); i++) {
            Point rotatePoint = currentFigure.getRotatePoints().get(i);

            if (Math.pow(rotatePoint.getX() - point.getX(), 2) + Math.pow(rotatePoint.getY() - point.getY(), 2) <= 80) {

                final Scene scene = currentCanvasController.getDrawCanvas().getScene();
                switch (i) {
                    case 0 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.NW, currentCanvasController.getCurrentFigure().get().getAngle()));
                    case 1 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.SW, currentCanvasController.getCurrentFigure().get().getAngle()));
                    case 2 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.SE, currentCanvasController.getCurrentFigure().get().getAngle()));
                    case 3 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.NE, currentCanvasController.getCurrentFigure().get().getAngle()));
                }
                mainController.setCurrentTool(new RotateTool(mainController));
                return;
            }
        }
        mainController.setCurrentTool(new SelectTool(mainController));
        currentCanvasController.getDrawCanvas().getScene().setCursor(Cursor.DEFAULT);
    }
}
