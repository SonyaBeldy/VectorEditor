package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.*;
import com.example.vectoreditor.model.figures.Figure;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class SelectTool extends Tool implements ITool {

    public SelectTool(MainController mainController) {
        super(mainController);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (mainController.getCurrentCanvasController().isEmpty()) {
            return;
        }
        mainController.getCurrentCanvasController().redrawAllFigures();
        Optional<Figure> figure = enteredFigure(event);
        figure.ifPresent(value -> value.highlight(mainController.getCurrentCanvasController().getDrawCanvas().getGraphicsContext2D()));
        bordersControlsEntered(event);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Optional <FigureItemController> figureController = selectFigure(event);

        if (figureController.isEmpty()) {
            //снимаем выделение с текущей фигуры
            //selectionFrame tool (или в драге)
            mainController.setCurrentTool(new SelectionFrameTool(mainController));
            mainController.getCurrentTool().mousePressed(event);
            return;
        }
        //нашли фигуру
        //выделили ее
        mainController.getPropertiesBoxController().update();
        mainController.getCurrentCanvasController().redrawAllFigures(); //убрать перерисовку
        mainController.setCurrentTool(new MoveTool(mainController, new Point(event.getX(), event.getY())));
    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (mainController.getCurrentCanvasController().getCurrentFigureController().isEmpty()) {
            return;
        }
        mainController.getCurrentCanvasController().redrawAllFigures();
    }

    private Optional<FigureItemController> selectFigure(MouseEvent event) {
        LayerBox layerBox = mainController.getCurrentCanvasController().getLayerBox();
        double x = event.getX();
        double y = event.getY();

        for (int i = layerBox.getLayers().size() - 1; i >= 0; i--) {
            LayerItemController layer = layerBox.getLayers().get(i);
            for (int j = layer.getFiguresCount() - 1; j >= 0; j--) {
                FigureItemController figureController = layer.getFigureController(j);
                if(figureController.getFigure().isClickedOn(new Point(x, y))) {
                    mainController.getCurrentCanvasController().selectFigure(layerBox.getLayers().get(i), figureController);
                    layer.layerItemClick();//сделать ли это в методе контроллера или мейна? Или оставить тут?
                    figureController.figureItemClick(); //сделать ли это в методе контроллера или мейна? Или оставить тут?
                    return Optional.of(figureController);
                }
            }
        }
        mainController.getCurrentCanvasController().removeFigureSelection(); //излишнее действие, метод только определяет, по
        //какому элементу кликнули, перенести это действие в pressed
        return Optional.empty();
    }
    private Optional<Figure> enteredFigure(MouseEvent event) {
        LayerBox layerBox = mainController.getCurrentCanvasController().getLayerBox();
        double x = event.getX();
        double y = event.getY();

        for (int i = layerBox.getLayers().size() - 1; i >= 0; i--) {
            LayerItemController layer = layerBox.getLayers().get(i);
            for (int j = layer.getFiguresCount() - 1; j >= 0; j--) {
                Figure figure = layer.getFigureController(j).getFigure();
                if(figure.isClickedOn(new Point(x, y))) {
                    return Optional.of(figure);
                }
            }
        }
        return Optional.empty();
    }

//    private void hitResizePivot(Figure figure, Point eventPoint) {
//        Cursor cursor = Cursor.DEFAULT;
//        Point dragPoint = new Point(0,0);
//        Point oppositePoint = new Point(0,0);
//
//
//        if(figure.getFrame().hitLeftTop(new Point(eventPoint.getX(), eventPoint.getY()))) {
//            cursor = Cursor.NW_RESIZE;
//            dragPoint = figure.getFrame().getLeftTop();
//            oppositePoint = figure.getFrame().getRightBot();
//        } else if (figure.getFrame().hitRightTop(new Point(eventPoint.getX(), eventPoint.getY()))) {
//            cursor = Cursor.NE_RESIZE;
//            dragPoint = figure.getFrame().getRightTop();
//            oppositePoint = figure.getFrame().getLeftBot();
//        }
//
//        mainController.getCurrentCanvasController().getDrawCanvas().getScene().setCursor(cursor);
//        if (cursor != Cursor.DEFAULT) {
//            mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.XY));
//        }
//    }
    private void bordersControlsEntered(MouseEvent event) {
        CanvasViewController currentCanvasController = mainController.getCurrentCanvasController();
        Point point = new Point(event.getX(), event.getY());
        if (currentCanvasController.getCurrentFigureController().isEmpty()) {
            return;
        }
        Figure currentFigure = currentCanvasController.getCurrentFigureController().get().getFigure();

        Cursor cursor = Cursor.DEFAULT;
//        Point dragPoint = new Point(0,0);
//        Point oppositePoint = new Point(0,0);

//        hitResizePivot(figure, new Point(event.getX(), event.getY()));
//        hitRotate(figure, new Point(event.getX(), event.getY()));

//        for (int i = 0; i < currentFigure.getFrame().getPivotsPoints().size(); i++) {
//            Point boardCorner = currentFigure.getFrame().getPivotsPoints().get(i);


//            currentCanvasController.getDrawCanvas().getScene().setCursor(cursor);
//            mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.XY));

//            if (Math.pow(boardCorner.getX() - point.getX(), 2) + Math.pow(boardCorner.getY() - point.getY(), 2) <= 20) {
//                switch (i) {
//                    case 0 -> {
//                        cursor = Cursor.NW_RESIZE;
//                        dragPoint = currentFigure.getBoardsPoints().get(0);
//                        oppositePoint = currentFigure.getBoardsPoints().get(2);
//                    }
//                    case 1 -> {
//                        cursor = Cursor.NE_RESIZE;
//                        dragPoint = currentFigure.getBoardsPoints().get(1);
//                        oppositePoint = currentFigure.getBoardsPoints().get(3);
//                    }
//                    case 2 -> {
//                        cursor = Cursor.SE_RESIZE;
//                        dragPoint = currentFigure.getBoardsPoints().get(2);
//                        oppositePoint = currentFigure.getBoardsPoints().get(0);
//                    }
//                    case 3 -> {
//                        cursor = Cursor.SW_RESIZE;
//                        dragPoint = currentFigure.getBoardsPoints().get(3);
//                        oppositePoint = currentFigure.getBoardsPoints().get(1);
//                    }
//                }
//                currentCanvasController.getDrawCanvas().getScene().setCursor(cursor);
//                mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.XY));
//                return;
//            }
//        }
//        mainController.setCurrentTool(new SelectTool(mainController));
//        for (int i = 0; i < currentFigure.getResizePoints().size(); i++) {
//            Point boardCorner = currentFigure.getResizePoints().get(i);
//            if (Math.pow(boardCorner.getX() - point.getX(), 2) + Math.pow(boardCorner.getY() - point.getY(), 2) <= Values.RESIZE_HITBOX) {
//                switch (i) {
//                    case 0 -> {
//                        cursor = Cursor.N_RESIZE;
//                        dragPoint = currentFigure.getResizePoints().get(0);
//                        oppositePoint = currentFigure.getResizePoints().get(2);
//                        mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.Y));
//                    }
//                    case 1 -> {
//                        cursor = Cursor.E_RESIZE;
//                        dragPoint = currentFigure.getResizePoints().get(1);
//                        oppositePoint = currentFigure.getResizePoints().get(3);
//                        mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint , ResizeDirection.X));
//                    }
//                    case 2 -> {
//                        cursor = Cursor.S_RESIZE;
//                        dragPoint = currentFigure.getResizePoints().get(2);
//                        oppositePoint = currentFigure.getResizePoints().get(0);
//                        mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.Y));
//                    }
//                    case 3 -> {
//                        cursor = Cursor.W_RESIZE;
//                        dragPoint = currentFigure.getResizePoints().get(3);
//                        oppositePoint = currentFigure.getResizePoints().get(1);
//                        mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.X));
//                    }
//                }
//                currentCanvasController.getDrawCanvas().getScene().setCursor(cursor);
//                return;
//            }
//        }
//        for (int i = 0; i < currentFigure.getRotatePoints().size(); i++) {
//            Point rotatePoint = currentFigure.getRotatePoints().get(i);
//
//            if (Math.pow(rotatePoint.getX() - point.getX(), 2) + Math.pow(rotatePoint.getY() - point.getY(), 2) <= 80) {
//
//                final Scene scene = currentCanvasController.getDrawCanvas().getScene();
//                switch (i) {
//                    case 0 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.NW, currentCanvasController.getCurrentFigureController().get().getFigure().getTransformProperties().getAngle()));
//                    case 1 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.SW, currentCanvasController.getCurrentFigureController().get().getFigure().getTransformProperties().getAngle()));
//                    case 2 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.SE, currentCanvasController.getCurrentFigureController().get().getFigure().getTransformProperties().getAngle()));
//                    case 3 -> scene.setCursor(CursorImage.rotateCursor(BorderPointInd.NE, currentCanvasController.getCurrentFigureController().get().getFigure().getTransformProperties().getAngle()));
//                }
//                mainController.setCurrentTool(new RotateTool(mainController));
//                return;
//            }
//        }
        mainController.setCurrentTool(new SelectTool(mainController));
        currentCanvasController.getDrawCanvas().getScene().setCursor(Cursor.DEFAULT);
    }

    private void hitRotate(Figure figure, Point eventPoint) {
        final Scene scene = mainController.getCurrentCanvasController().getDrawCanvas().getScene();
        Frame frame2 = new Frame(figure);
        if(frame2.hitCenterLeftRotate(eventPoint)) {
            scene.setCursor(CursorImage.rotateCursor(BorderPointInd.NW,  mainController.getCurrentCanvasController().getCurrentFigureController().get().getFigure().getTransformProperties().getAngle()));
        }
    }
}
