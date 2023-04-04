package com.example.vectoreditor.model.unused;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.Frame;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.drawers.Drawer;
import com.example.vectoreditor.model.figures.FigureDecorationData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class FrameDrawer extends Drawer {
    private final GraphicsContext graphicsContext;

    public FrameDrawer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void draw(GraphicsContext graphicsContext, FigureTransformData figureTransformData, FigureDecorationData figureDecorationData) {
        super.draw(graphicsContext, figureTransformData, figureDecorationData);
    }

    public void draw(Frame frame, Color layerColor) {
        drawEdges(frame.getEdgesPoints(), layerColor);
        drawPivots(frame.getPivotsPoints(), layerColor);
        drawCenter(frame.getCenter(), layerColor);
    }

    private void drawPivots(List<Point> points, Color layerColor) {
        double PIVOT_W = 8;
        double PIVOT_H = 8;
        for (Point point : points) {
            graphicsContext.setFill(layerColor);
            graphicsContext.fillRect(point.getX() - PIVOT_W / 2, point.getY() - PIVOT_H / 2, PIVOT_W, PIVOT_H);
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect(point.getX() - PIVOT_W / 4, point.getY() - PIVOT_H / 4, PIVOT_W / 2, PIVOT_H / 2);
        }
    }

    private void drawEdges(List<Point> points, Color layerColor) {
        graphicsContext.setStroke(layerColor);

        for (int i = 0; i < points.size() - 1; i++) {
            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
        }
        graphicsContext.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY());
    }

    private void drawCenter(Point center, Color layerColor) {
        graphicsContext.setFill(layerColor);
        graphicsContext.fillOval(center.getX(), center.getY(), 3,3);
    }
}
