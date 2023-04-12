package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.*;
import com.example.vectoreditor.model.figures.Figure;
import com.example.vectoreditor.model.SingleFrame;
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

//        if (mainController.getCurrentCanvasController().getSelectedFiguresList().size() > 1) {
//            mainController.setCurrentTool(new MoveTool(mainController, new Point(event.getX(), event.getY())));
//            return;
//        }

        Optional <FigureItemController> figureController = selectFigure(event);

        if (figureController.isEmpty()) {
            mainController.setCurrentTool(new SelectionFrameTool(mainController));
            mainController.getCurrentTool().mousePressed(event);
            return;
        }

        System.out.println("s size " + mainController.getCurrentCanvasController().getSelectedFiguresList().size());

        //нашли фигуру
        //выделили ее
        mainController.getCurrentCanvasController().redrawAllFigures(); //убрать перерисовку
        mainController.setCurrentTool(new MoveTool(mainController, new Point(event.getX(), event.getY())));
    }

    @Override
    public void mouseDragged(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (mainController.getCurrentCanvasController().getSelectedFiguresList().isEmpty()) {
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

                    /////////////////удалить
//                    for (int k = 0; k < layer.getFiguresCount(); k++) {
//                        mainController.getCurrentCanvasController().getSelectedFiguresList().add(layer.getFigureController(k));
//                    }
                    ////////////////

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

    private void changeCursor(Cursor cursor) {
        mainController.getCurrentCanvasController().getDrawCanvas().getScene().setCursor(cursor);
    }
    private boolean hitResizePivot(Frame frame, Point eventPoint) {
        Point dragPoint;
        Point oppositePoint;

        if (frame.hitLeftTop(new Point(eventPoint.getX(), eventPoint.getY()))) {
            dragPoint = frame.getLeftTop();
            oppositePoint = frame.getRightBot();

            mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.XY));
            changeCursor(Cursor.NW_RESIZE);
            return true;

        } else if (frame.hitRightTop(new Point(eventPoint.getX(), eventPoint.getY()))) {
            dragPoint = frame.getRightTop();
            oppositePoint = frame.getLeftBot();

            mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.XY));
            changeCursor(Cursor.NE_RESIZE);
            return true;

        } else if (frame.hitRightBot(new Point(eventPoint.getX(), eventPoint.getY()))) {
            dragPoint = frame.getRightBot();
            oppositePoint = frame.getLeftTop();

            mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.XY));
            changeCursor(Cursor.SE_RESIZE);
            return true;

        } else if (frame.hitLeftBot(new Point(eventPoint.getX(), eventPoint.getY()))) {
            dragPoint = frame.getLeftBot();
            oppositePoint = frame.getRightTop();

            mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.XY));
            changeCursor(Cursor.SW_RESIZE);
            return true;
        } else if (frame.hitCenterTop(new Point(eventPoint.getX(), eventPoint.getY()))) {
            dragPoint = frame.getCenterTop();
            oppositePoint = frame.getCenterBot();

            mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.Y));
            changeCursor(Cursor.N_RESIZE);
            return true;
        } else if (frame.hitCenterRight(new Point(eventPoint.getX(), eventPoint.getY()))) {
            dragPoint = frame.getCenterRight();
            oppositePoint = frame.getCenterLeft();

            mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.X));
            changeCursor(Cursor.E_RESIZE);
            return true;
        } else if (frame.hitCenterBot(new Point(eventPoint.getX(), eventPoint.getY()))) {
            dragPoint = frame.getCenterBot();
            oppositePoint = frame.getCenterTop();

            mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.Y));
            changeCursor(Cursor.S_RESIZE);
            return true;
        } else if (frame.hitCenterLeft(new Point(eventPoint.getX(), eventPoint.getY()))) {
            dragPoint = frame.getCenterLeft();
            oppositePoint = frame.getCenterRight();

            mainController.setCurrentTool(new ResizeTool(mainController, dragPoint, oppositePoint, ResizeDirection.X));
            changeCursor(Cursor.W_RESIZE);
            return true;
        }
        return false;
    }
    private void bordersControlsEntered(MouseEvent event) {
        CanvasViewController currentCanvasController = mainController.getCurrentCanvasController();
        if (currentCanvasController.getSelectedFiguresList().isEmpty()) {
            return;
        }
        Figure currentFigure = currentCanvasController.getSelectedFiguresList().getLast().getFigure();
        Frame frame = new SingleFrame(currentFigure);

        if (!hitResizePivot(frame, new Point(event.getX(), event.getY())) && !hitRotate(frame, new Point(event.getX(), event.getY()))) {
            changeCursor(Cursor.DEFAULT);
            mainController.setCurrentTool(new SelectTool(mainController));
        }
    }

    private boolean hitRotate(Frame frame, Point eventPoint) {
        final Scene scene = mainController.getCurrentCanvasController().getDrawCanvas().getScene();

        int borderPointInd = -1;

        if (frame.hitLeftTopRotate(eventPoint)) {
            borderPointInd = BorderPointInd.NW;
        } else if (frame.hitRightTop(eventPoint)) {
            borderPointInd = BorderPointInd.NE;
        } else if (frame.hitRightBotRotate(eventPoint)) {
            borderPointInd = BorderPointInd.SE;
        } else if (frame.hitLeftBotRotate(eventPoint)) {
            borderPointInd = BorderPointInd.SW;
        }

        if (borderPointInd != -1) {
            mainController.setCurrentTool(new RotateTool(mainController));
            scene.setCursor(CursorImage.rotateCursor(borderPointInd,
                    ////////////
                    mainController.getCurrentCanvasController().getSelectedFiguresList().getLast().getFigure().getTransformProperties().getAngle())); //может стать причиной ошибки
                    //////////////
            return true;
        }
        return false;

    }
}
