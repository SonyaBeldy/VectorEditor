package com.example.vectoreditor.model;

import com.example.vectoreditor.model.figure.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BordersPainter {

    GraphicsContext graphicsContext;

    public BordersPainter(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void drawBoards(Figure figure) {

        ArrayList<Point> points = figure.getBoardsPoints();
        graphicsContext.setStroke(Color.MEDIUMSLATEBLUE);
        graphicsContext.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(3).getX(), points.get(3).getY());
        for (int i = 0; i < points.size() - 1; i++) {
            String a;
            //graphicsContext.setStroke(Color.web("#c6ff42"));
            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());

            graphicsContext.setFill(Color.SLATEBLUE);
            graphicsContext.fillRect(points.get(i).getX() - 4, points.get(i).getY() - 4, 8, 8);
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect(points.get(i).getX() - 2, points.get(i).getY() - 2, 4, 4);
        }


        graphicsContext.setFill(Color.SLATEBLUE);
        graphicsContext.fillRect(points.get(3).getX() - 4, points.get(3).getY() - 4, 8, 8);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(points.get(3).getX() - 2, points.get(3).getY() - 2, 4, 4);


        graphicsContext.setFill(Color.SLATEBLUE);
        graphicsContext.fillOval(figure.getCenter().getX() - 2, figure.getCenter().getY() - 2, 4, 4);

        for (int i = 0; i < points.size() - 1; i++) {

            double minX = Math.min(points.get(i).getX(), points.get(i + 1).getX());
            double maxX = Math.max(points.get(i).getX(), points.get(i + 1).getX());

            double minY = Math.min(points.get(i).getY(), points.get(i + 1).getY());
            double maxY = Math.max(points.get(i).getY(), points.get(i + 1).getY());

            //graphicsContext.setStroke(Color.web("#c6ff42"));
            //graphicsContext.setStroke(Color.MEDIUMSLATEBLUE);
            //graphicsContext.strokeLine((maxX - minX)/2 + minX, points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());

            graphicsContext.setFill(Color.SLATEBLUE);
            graphicsContext.fillRect((maxX - minX)/2 + minX - 4, (maxY - minY)/2 + minY - 4, 8, 8);
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillRect((maxX - minX)/2 + minX - 2, (maxY - minY)/2 + minY - 2, 4, 4);
        }

        double minX = Math.min(points.get(0).getX(), points.get(3).getX());
        double maxX = Math.max(points.get(0).getX(), points.get(3).getX());

        double minY = Math.min(points.get(0).getY(), points.get(3).getY());
        double maxY = Math.max(points.get(0).getY(), points.get(3).getY());

        graphicsContext.setFill(Color.SLATEBLUE);
        graphicsContext.fillRect((maxX - minX)/2 + minX - 4, (maxY - minY)/2 + minY - 4, 8, 8);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect((maxX - minX)/2 + minX - 2, (maxY - minY)/2 + minY - 2, 4, 4);

    }

//    public void drawBoards(Figure figure) {
//
//        ArrayList<Point> points = figure.getBoardsPoints();
//        for (int i = 0; i < points.size() - 1; i++) {
//            //graphicsContext.setStroke(Color.web("#c6ff42"));
//            graphicsContext.setStroke(Color.MEDIUMSLATEBLUE);
//            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
//
//            graphicsContext.setFill(Color.SLATEBLUE);
//            graphicsContext.fillRect(points.get(i).getX() - 4, points.get(i).getY() - 4, 8, 8);
//            graphicsContext.setFill(Color.WHITE);
//            graphicsContext.fillRect(points.get(i).getX() - 2, points.get(i).getY() - 2, 4, 4);
//        }
//        graphicsContext.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(3).getX(), points.get(3).getY());
//
//        graphicsContext.setFill(Color.SLATEBLUE);
//        graphicsContext.fillRect(points.get(3).getX() - 4, points.get(3).getY() - 4, 8, 8);
//        graphicsContext.setFill(Color.WHITE);
//        graphicsContext.fillRect(points.get(3).getX() - 2, points.get(3).getY() - 2, 4, 4);
//
//
//        double centerX = ListUtils.getCenter(figure.getPoints()).getX();
//        double centerY = ListUtils.getCenter(figure.getPoints()).getY();
//
//        graphicsContext.setFill(Color.SLATEBLUE);
//        graphicsContext.fillOval(centerX - 2, centerY - 2, 4, 4);
//
//        for (int i = 0; i < points.size() - 1; i++) {
//
//            double minX = Math.min(points.get(i).getX(), points.get(i + 1).getX());
//            double maxX = Math.max(points.get(i).getX(), points.get(i + 1).getX());
//
//            double minY = Math.min(points.get(i).getY(), points.get(i + 1).getY());
//            double maxY = Math.max(points.get(i).getY(), points.get(i + 1).getY());
//
//            //graphicsContext.setStroke(Color.web("#c6ff42"));
//            //graphicsContext.setStroke(Color.MEDIUMSLATEBLUE);
//            //graphicsContext.strokeLine((maxX - minX)/2 + minX, points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
//
//            graphicsContext.setFill(Color.SLATEBLUE);
//            graphicsContext.fillRect((maxX - minX)/2 + minX - 4, (maxY - minY)/2 + minY - 4, 8, 8);
//            graphicsContext.setFill(Color.WHITE);
//            graphicsContext.fillRect((maxX - minX)/2 + minX - 2, (maxY - minY)/2 + minY - 2, 4, 4);
//        }
//
//        double minX = Math.min(points.get(0).getX(), points.get(3).getX());
//        double maxX = Math.max(points.get(0).getX(), points.get(3).getX());
//
//        double minY = Math.min(points.get(0).getY(), points.get(3).getY());
//        double maxY = Math.max(points.get(0).getY(), points.get(3).getY());
//
//        graphicsContext.setFill(Color.SLATEBLUE);
//        graphicsContext.fillRect((maxX - minX)/2 + minX - 4, (maxY - minY)/2 + minY - 4, 8, 8);
//        graphicsContext.setFill(Color.WHITE);
//        graphicsContext.fillRect((maxX - minX)/2 + minX - 2, (maxY - minY)/2 + minY - 2, 4, 4);
//
//    }

    private void drawFaceCenterPoints(){

    }

}
