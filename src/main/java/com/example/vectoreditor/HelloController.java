package com.example.vectoreditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button lineButton;

    @FXML
    private Button selectButton;

    @FXML
    private Canvas drawCanvas;

    public static Figure currentFigure;
    public static ArrayList<Figure> figures = new ArrayList<>();
    public static Tool currentState = Tool.SELECTION;

    private Point clickPoint = new Point(0,0);

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onLineButtonClick(ActionEvent event) {
        currentState = Tool.LINE;
    }

    @FXML
    protected void onSelectButtonClick(ActionEvent event) {
        currentState = Tool.SELECTION;
    }

    @FXML
    void onCanvasClick(MouseEvent event){


        switch (currentState) {
            case SELECTION:
                currentFigure = whatWasClickedOn(event);
                if (currentFigure != null) {
                    redrawAllFigures();
                    currentFigure.highlightCurrant(drawCanvas.getGraphicsContext2D());
                    clickPoint.x = event.getX();
                    clickPoint.y = event.getY();
                }
                break;
            case LINE:
                double x = event.getX();
                double y = event.getY();

                Line line = new Line(x,y, x, y);
                currentFigure = line;
                line.draw(drawCanvas.getGraphicsContext2D());
                break;


        }

    }
    @FXML
    void onCanvasDragged(MouseEvent event) {
        switch (currentState) {
            case SELECTION:
                double differenceX = event.getX() - clickPoint.x;
                double differenceY = event.getY() - clickPoint.y;

                Point s = new Point(currentFigure.getStartPoint().getX() + differenceX, currentFigure.getStartPoint().getY() + differenceY);
                Point e = new Point(currentFigure.getEndPoint().getX() + differenceX, currentFigure.getEndPoint().getY() + differenceY);

                redrawFiguresWithoutCurrant();
                currentFigure.draw(drawCanvas.getGraphicsContext2D(), s, e);
                currentFigure.highlightCurrant(drawCanvas.getGraphicsContext2D(), s, e);

                break;
            case LINE:
                double x = event.getX();
                double y = event.getY();

                currentFigure.setEndPoint(x, y);
                redrawAllFigures();

                currentFigure.draw(drawCanvas.getGraphicsContext2D());


                break;
        }

    }
    @FXML
    void onCanvasReleased(MouseEvent event){
        switch (currentState) {
            case SELECTION:
                double differenceX = event.getX() - clickPoint.x;
                double differenceY = event.getY() - clickPoint.y;

                Point s = new Point(currentFigure.getStartPoint().getX() + differenceX, currentFigure.getStartPoint().getY() + differenceY);
                Point e = new Point(currentFigure.getEndPoint().getX() + differenceX, currentFigure.getEndPoint().getY() + differenceY);


                currentFigure.setStartPoint(s);
                currentFigure.setEndPoint(e);
                redrawAllFigures();
                currentFigure.draw(drawCanvas.getGraphicsContext2D());
                currentFigure.highlightCurrant(drawCanvas.getGraphicsContext2D());


                break;
            case LINE:
                double x = event.getX();
                double y = event.getY();
                redrawAllFigures();
                currentFigure.setEndPoint(x, y);
                currentFigure.draw(drawCanvas.getGraphicsContext2D());
                currentFigure.highlightCurrant(drawCanvas.getGraphicsContext2D());

                //isDrawLine = false;
                figures.add(currentFigure);
                break;
        }

    }




    @FXML
    void initialize() {


    }

    void redrawAllFigures() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (int i = 0; i < figures.size(); i++) {
            figures.get(i).draw(drawCanvas.getGraphicsContext2D());
        }
    }
    void redrawFiguresWithoutCurrant() {
        drawCanvas.getGraphicsContext2D().clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
        for (Figure figure : figures) {
            if (figure != currentFigure) {
                figure.draw(drawCanvas.getGraphicsContext2D());
            }

        }
    }
    Figure whatWasClickedOn(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        for (int i = figures.size() - 1; i >= 0; i--) {
            if(figures.get(i).inHitBox(x, y)){
                System.out.println("inside");
                return figures.get(i);
            }
        }
        return null;
    }

}