package com.example.vectoreditor.controller.unused;

import com.example.vectoreditor.controller.MainController;

public class CurrentFigureParamsDisplay {

    MainController mainController;

    public CurrentFigureParamsDisplay(MainController mainController) {
        this.mainController = mainController;
    }
//    public void update(CanvasViewController canvasController) {
//        updateRotateField(canvasController);
//        updateXPointField(canvasController);
//        updateYPointField(canvasController);
//        updateWidth(canvasController);
//        updateHeight(canvasController);
//    }
//    public void update() {
//        updateRotateField();
//        updateXPointField();
//        updateYPointField();
//        updateWidth();
//        updateHeight();
//    }
//
//    private void updateRotateField(CanvasViewController canvasController) {
//        if(canvasController.getCurrentFigureController().isPresent()) {
//            Figure figure = canvasController.getCurrentFigureController().get().getFigure();
//            double angle = Math.round(Math.toDegrees(figure.getAngle()));
//            if (angle < 0) {
//                angle+= 360;
//            }
//            if (angle > 360) {
//                angle-= 360;
//            }
//            mainController.getRotate().setText(String.valueOf(angle));
//        }
//    }
//    private void updateRotateField() {
//        if(mainController.getCurrentCanvasController().getCurrentFigureController().isPresent()) {
//            Figure figure = mainController.getCurrentCanvasController().getCurrentFigureController().get().getFigure();
//            double angle = Math.round(Math.toDegrees(figure.getAngle()));
//            if (angle < 0) {
//                angle+= 360;
//            }
//            if (angle > 360) {
//                angle-= 360;
//            }
//           mainController.getRotate().setText(String.valueOf(angle));
//        }
//    }
//    private void updateXPointField(CanvasViewController canvasController) {
//        if(canvasController.getCurrentFigureController().isPresent()) {
//            Figure figure = canvasController.getCurrentFigureController().get().getFigure();
//            mainController.getXPointField().setText(String.valueOf(figure.getCenter().getX()));
//        }
//    }
//    private void updateXPointField() {
//        if(mainController.getCurrentCanvasController().getCurrentFigureController().isPresent()) {
//            Figure figure = mainController.getCurrentCanvasController().getCurrentFigureController().get().getFigure();
//            mainController.getXPointField().setText(String.valueOf(figure.getCenter().getX()));
//        }
//    }
//    private void updateYPointField(CanvasViewController canvasController) {
//        if(canvasController.getCurrentFigureController().isPresent()) {
//            Figure figure = canvasController.getCurrentFigureController().get().getFigure();
//            mainController.getYPointField().setText(String.valueOf(figure.getCenter().getY()));
//        }
//    }
//    private void updateYPointField() {
//        if(mainController.getCurrentCanvasController().getCurrentFigureController().isPresent()) {
//            Figure figure = mainController.getCurrentCanvasController().getCurrentFigureController().get().getFigure();
//            mainController.getYPointField().setText(String.valueOf(figure.getCenter().getY()));
//        }
//    }
//
//    private void updateWidth(CanvasViewController canvasController) {
//        if(canvasController.getCurrentFigureController().isPresent()) {
//            Figure figure = canvasController.getCurrentFigureController().get().getFigure();
//            double width = PointListUtils.calcDist(figure.getBoardsPoints().get(0), figure.getBoardsPoints().get(1));
//            mainController.getWidthField().setText(String.valueOf(width));
//        }
//    }
//    private void updateWidth() {
//        if(mainController.getCurrentCanvasController().getCurrentFigureController().isPresent()) {
//            Figure figure = mainController.getCurrentCanvasController().getCurrentFigureController().get().getFigure();
//            double width = PointListUtils.calcDist(figure.getBoardsPoints().get(0), figure.getBoardsPoints().get(1));
//            mainController.getWidthField().setText(String.valueOf(width));
//        }
//    }
//
//    private void updateHeight(CanvasViewController canvasController) {
//        if(canvasController.getCurrentFigureController().isPresent()) {
//            Figure figure = canvasController.getCurrentFigureController().get().getFigure();
//            double height = PointListUtils.calcDist(figure.getBoardsPoints().get(0), figure.getBoardsPoints().get(3));
//            mainController.getHeightField().setText(String.valueOf(height));
//        }
//    }
//    private void updateHeight() {
//        if(mainController.getCurrentCanvasController().getCurrentFigureController().isPresent()) {
//            Figure figure = mainController.getCurrentCanvasController().getCurrentFigureController().get().getFigure();
//            double height = PointListUtils.calcDist(figure.getBoardsPoints().get(0), figure.getBoardsPoints().get(3));
//            mainController.getHeightField().setText(String.valueOf(height));
//        }
//    }
//
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
}
