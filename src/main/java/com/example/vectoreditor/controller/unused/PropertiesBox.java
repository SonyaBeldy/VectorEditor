package com.example.vectoreditor.controller.unused;

import com.example.vectoreditor.controller.FigureItemController;
import com.example.vectoreditor.controller.PropertiesBoxController;
import com.example.vectoreditor.model.PointListUtils;

import java.util.Optional;

public class PropertiesBox {

    PropertiesBoxController controller;

    public void update(Optional<FigureItemController> figureItemController) {
        updateRotateField(figureItemController);
        updateXPointField(figureItemController);
        updateYPointField(figureItemController);
//        updateWidth(figureItemController);
//        updateHeight(figureItemController);
    }

    private void updateRotateField(Optional<FigureItemController> figureItemController) {
        if(figureItemController.isEmpty()) {
            controller.getRotateField().setText("");
            return;
        }
            double angle = Math.round(Math.toDegrees(figureItemController.get().getFigure().getTransformProperties().getAngle()));
            if (angle < 0) {
                angle+= 360;
            }
            if (angle > 360) {
                angle-= 360;
            }
            controller.getRotateField().setText(String.valueOf(angle));
    }

    private void updateXPointField(Optional<FigureItemController> figureItemController) {
        if (figureItemController.isEmpty()) {
            controller.getXPointField().setText("");
        } else {
            controller.getXPointField().setText(String.valueOf(figureItemController.get().getFigure().getCenter().getX()));
        }
    }

    private void updateYPointField(Optional<FigureItemController> figureItemController) {
        if(figureItemController.isEmpty()) {
            controller.getYPointField().setText("");
        } else {
            controller.getYPointField().setText(String.valueOf(figureItemController.get().getFigure().getCenter().getY()));
        }
    }

//    private void updateWidth(Optional<FigureItemController> figureItemController) {
//        if(figureItemController.isEmpty()) {
//            controller.getWidthField().setText("");
//        } else {
//            double width = PointListUtils.calcDist(figureItemController.get().getFigure().getFrame().getEdgesPoints().get(0), figureItemController.get().getFigure().getFrame().getEdgesPoints().get(1));
//            controller.getWidthField().setText(String.valueOf(width));
//        }
//
//    }
//
//    private void updateHeight(Optional<FigureItemController> figureItemController) {
//        if(figureItemController.isEmpty()) {
//            controller.getHeightField().setText("");
//        } else {
//            double height = PointListUtils.calcDist(figureItemController.get().getFigure().getFrame().getEdgesPoints().get(0), figureItemController.get().getFigure().getFrame().getEdgesPoints().get(3));
//            controller.getHeightField().setText(String.valueOf(height));
//        }
//    }

}
