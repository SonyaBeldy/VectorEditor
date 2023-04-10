package com.example.vectoreditor.model;

import com.example.vectoreditor.controller.FigureItemController;
import com.example.vectoreditor.model.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class SelectedFigureControllsList extends ArrayList<FigureItemController> {

    public List<Figure> getAllFigures() {
        List<Figure> figureList = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            figureList.add(get(i).getFigure());
        }
        return figureList;
    }

    public Figure getFigure(int i) {
        return get(i).getFigure();
    }

    public boolean isSingle() {
        return size() == 1;
    }

    public FigureItemController getLast() {
        return get(size() - 1);
    }

    public void setSelected(FigureItemController figureItemController) {
        clear();
        add(figureItemController);
    }

    public void move(Point difference) {
        for (int i = 0; i < size(); i++) {
            get(i).getFigure().move(difference);
        }
    }

    public void rotate(Figure copyCurrentFigure) {

    }

    public List<Figure> cloneFigures(){
//        List<Figure> clone = (List<Figure>) super.clone();
        List<Figure> figures = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            figures.add(get(i).getFigure().clone());
        }
        return figures;
    }

    public void calcCenter() {
        if(isSingle()) {
            getLast().getFigure().calcCenter();
        } else {
            for (int i = 0; i < size(); i++) {
                get(i).getFigure().calcCenter();
            }
        }
    }

    public Point getCenter() {
        if(isSingle()) {
            return getLast().getFigure().getCenter();
        } else {
            GroupFrame groupFrame = new GroupFrame(getAllFigures());
            return groupFrame.getCenter();
        }
    }
}
