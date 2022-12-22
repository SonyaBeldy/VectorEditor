package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Figure;
import com.example.vectoreditor.model.Point;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class SelectTool extends Tool implements ITool {

    public SelectTool(CanvasController canvasController) {
        super(canvasController);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Figure currentFigure = canvasController.whatWasClickedOn(event);
        if (currentFigure == null) {
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
        if (canvasController.getCurrentFigure() == null) {
            return;
        }
        canvasController.redrawAllFigures();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        System.out.println(event.getX() + " " + event.getY());
        if (canvasController.getFigures().isEmpty()) {
            return;
        }
        canvasController.redrawAllFigures();
        Figure enteredFigure = canvasController.whatWasClickedOn(event);
        if (enteredFigure != null) {
            enteredFigure.highlight(drawCanvas.getGraphicsContext2D());
        }

        bordersControlsEntered(event);
    }

    public Point bordersControlsEntered(MouseEvent event) {
        Point point = new Point(event.getX(), event.getY());
        Figure currentFigure = canvasController.getCurrentFigure();
        for (int i = 0; i < currentFigure.getBoardsPoints().size(); i++) {
            Point boardCorner = currentFigure.getBoardsPoints().get(i);
            if (Math.pow(boardCorner.getX() - point.getX(), 2) + Math.pow(boardCorner.getY() - point.getY(), 2) <= 20) {
                Cursor cursor = switch (i) {
                    case 0 -> Cursor.NW_RESIZE;
                    case 1 -> Cursor.NE_RESIZE;
                    case 2 -> Cursor.SE_RESIZE;
                    case 3 -> Cursor.SW_RESIZE;
                    default -> Cursor.DEFAULT;
                };
                canvasController.getDrawCanvas().getScene().setCursor(cursor);
                canvasController.setCurrentTool(new ResizeCornerTool(canvasController));
                return boardCorner;
            }
        }
        canvasController.setCurrentTool(new SelectTool(canvasController));
        for (int i = 0; i < currentFigure.getResizePoints().size(); i++) {
            Point boardCorner = currentFigure.getResizePoints().get(i);
            if (Math.pow(boardCorner.getX() - point.getX(), 2) + Math.pow(boardCorner.getY() - point.getY(), 2) <= 40) {
                switch (i) {
                    case 0 -> canvasController.getDrawCanvas().getScene().setCursor(Cursor.N_RESIZE);
                    case 1 -> canvasController.getDrawCanvas().getScene().setCursor(Cursor.E_RESIZE);
                    case 2 -> canvasController.getDrawCanvas().getScene().setCursor(Cursor.S_RESIZE);
                    case 3 -> canvasController.getDrawCanvas().getScene().setCursor(Cursor.W_RESIZE);
                }
                return boardCorner;
            }
        }
        for (int i = 0; i < currentFigure.getRotatePoints().size(); i++) {
            Point rotatePoint = currentFigure.getRotatePoints().get(i);

            if (Math.pow(rotatePoint.getX() - point.getX(), 2) + Math.pow(rotatePoint.getY() - point.getY(), 2) <= 80) {
                Image image;
                ImageCursor cursor;
                switch (i) {
                    case 0 -> {
                        image = new Image(String.valueOf(this.getClass().getResource("/com/example/vectoreditor/images/icons/NE3.png")));
                        cursor = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
                        canvasController.getDrawCanvas().getScene().setCursor(cursor);
                    }
                    case 1 -> {
                        image = new Image(String.valueOf(this.getClass().getResource("/com/example/vectoreditor/images/icons/NW3.png")));
                        cursor = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
                        canvasController.getDrawCanvas().getScene().setCursor(cursor);
                    }
                    case 2 -> {
                        image = new Image(String.valueOf(this.getClass().getResource("/com/example/vectoreditor/images/icons/SE3.png")));
                        cursor = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
                        canvasController.getDrawCanvas().getScene().setCursor(cursor);
                    }
                    case 3 -> {
                        image = new Image(String.valueOf(this.getClass().getResource("/com/example/vectoreditor/images/icons/WE3.png")));
                        cursor = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
                        canvasController.getDrawCanvas().getScene().setCursor(cursor);
                    }
                }
                canvasController.setCurrentTool(new RotateTool(canvasController));
                return rotatePoint;
            }
        }
        canvasController.setCurrentTool(new SelectTool(canvasController));
        canvasController.getDrawCanvas().getScene().setCursor(Cursor.DEFAULT);

        return null;
    }

}
