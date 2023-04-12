package com.example.vectoreditor.controller;

import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.PointListUtils;
import com.example.vectoreditor.model.figures.Figure;
import com.example.vectoreditor.model.figures.FigureDecorationData;
import com.example.vectoreditor.model.figures.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Optional;

public class SelectionFrameTool extends Tool implements ITool {

    Rectangle selectionFrame;
    public SelectionFrameTool(MainController mainController) {
        super(mainController);
        FigureDecorationData decorationData = new FigureDecorationData(Optional.of(Color.GRAY), Optional.empty(), 1);
        decorationData.setLineDashes(4);
//        frame = new Rectangle(mainController.getPropertiesBoxController().getDecorationProperties());
        selectionFrame = new Rectangle(decorationData);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        selectionFrame.getLeftTop().setX(event.getX());
        selectionFrame.getLeftTop().setY(event.getY());

        selectionFrame.getRightTop().setY(event.getY());

        selectionFrame.getLeftBot().setX(event.getX());

        System.out.println("size" + mainController.getCurrentCanvasController().getSelectedFiguresList().size());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        mainController.getCurrentCanvasController().redrawAllFigures();
        selectionFrame.getRightTop().setX(event.getX());
        selectionFrame.getRightBot().setX(event.getX());
        selectionFrame.getRightBot().setY(event.getY());
        selectionFrame.getLeftBot().setY(event.getY());
        GraphicsContext graphicsContext = mainController.getCurrentCanvasController().getDrawCanvas().getGraphicsContext2D();
//        graphicsContext.setLineCap(StrokeLineCap.BUTT);

        selectionFrame.draw(graphicsContext);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        updateFramePoints(selectionFrame);
        selectFigures();
        mainController.setCurrentTool(new SelectTool(mainController));
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    private void selectFigures() {
        for (int i = 0; i < mainController.getLayersCount(); i++) {
            LayerItemController layer = mainController.getLayers().get(i);
            for (int j = 0; j < layer.getFiguresCount(); j++) {
                if (isInSelectionFrame(layer.getFigureController(j).getFigure())) {
                    mainController.getCurrentCanvasController().getSelectedFiguresList().add(layer.getFigureController(j));
                }
            }
        }
    }

    private boolean isInSelectionFrame(Figure figure) {

        for (int i = 0; i < figure.getPoints().size(); i++) {
            Point point = figure.getPoints().get(i);
            if (!((point.getX() < selectionFrame.getRightBot().getX()) &&
                    (point.getY() < selectionFrame.getRightBot().getY()) &&
                    (point.getX() > selectionFrame.getLeftTop().getX()) &&
                    (point.getY() > selectionFrame.getLeftTop().getY()))
            ) {
                return false;
            }
        }
        return true;
    }

    private List<Point> updateFramePoints(Rectangle rectangle) {
        double minX = PointListUtils.calcMinX(rectangle.getPoints());
        double minY = PointListUtils.calcMinY(rectangle.getPoints());
        double maxX = PointListUtils.calcMaxX(rectangle.getPoints());
        double maxY = PointListUtils.calcMaxY(rectangle.getPoints());

        rectangle.getLeftTop().change(minX, minY);
        rectangle.getLeftBot().change(minX, maxY);
        rectangle.getRightTop().change(maxX, minY);
        rectangle.getRightBot().change(maxX, maxY);

        return rectangle.getPoints();

    }
}
