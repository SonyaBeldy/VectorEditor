package com.example.vectoreditor.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class BordersPainter {

    GraphicsContext graphicsContext;

    public BordersPainter(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void drawBoards(Figure figure) {

        ArrayList<Point> points = figure.getBoardsPoints();
        for (int i = 0; i < points.size() - 1; i++) {
            //graphicsContext.setStroke(Color.web("#c6ff42"));
            graphicsContext.setStroke(Color.MEDIUMSLATEBLUE);
            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());

            graphicsContext.setFill(Color.SLATEBLUE);
            graphicsContext.fillRect(points.get(i).getX() - 4, points.get(i).getY() - 4, 8, 8);
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect(points.get(i).getX() - 2, points.get(i).getY() - 2, 4, 4);
        }
        graphicsContext.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(3).getX(), points.get(3).getY());

        graphicsContext.setFill(Color.SLATEBLUE);
        graphicsContext.fillRect(points.get(3).getX() - 4, points.get(3).getY() - 4, 8, 8);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(points.get(3).getX() - 2, points.get(3).getY() - 2, 4, 4);


        double centerX = (points.get(2).getX() - points.get(0).getX())/2 + points.get(0).getX();
        double centerY = (points.get(2).getY() - points.get(0).getY())/2 + points.get(0).getY();

        graphicsContext.setFill(Color.SLATEBLUE);
        graphicsContext.fillOval(centerX - 2, centerY - 2, 4, 4);


    }

}
