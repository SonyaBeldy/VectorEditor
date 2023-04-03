package com.example.vectoreditor.model.unused;

import com.example.vectoreditor.controller.FigureTransformData;
import com.example.vectoreditor.model.Frame2;
import com.example.vectoreditor.model.Layer;
import com.example.vectoreditor.model.Point;
import com.example.vectoreditor.model.drawer.Drawer;
import com.example.vectoreditor.model.figure.FigureDecorationData;
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

    public void draw(Frame2 frame, Color layerColor) {
        drawEdges(frame.getEdgesPoints(), layerColor);
        drawPivots(frame.getPivotsPoints(), layerColor);
//        drawCenter(frame.);
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

        for (int i = 0; i < points.size(); i++) {
            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
        }
        graphicsContext.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(3).getX(), points.get(3).getY());
    }

    private void drawCenter(Point point) {

    }
}
