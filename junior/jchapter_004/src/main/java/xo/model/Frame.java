package xo.model;

import xo.model.interfaces.IFrame;
import xo.model.interfaces.IPoint;

public class Frame implements IFrame {

    private final Figure[][] figures;

    public Frame(int size) {
        this.figures = new Figure[size][size];
    }

    /**
     *
     * @return возвращает текущий массив Figure[][]
     */
    @Override
    public Figure[][] getFigures() {
        return this.figures;
    }

    /**
     *  Заполняет ячейку массива фигурой figure.
     * @param figure
     * @param point
     */
    @Override
    public void setFigure(Figure figure, IPoint point) {
        this.figures[point.getX()][point.getY()] = figure;
    }

    /**
     *
     * @param point
     * @return возвращает true, если значения в Point допустимы и ячейка массива Figure[][] не заполнена - null.
     */
    @Override
    public boolean accept(IPoint point) {
        return  point.getX() >= 0
                && point.getY() >= 0
                && point.getX() < this.figures.length
                && point.getY() < this.figures.length
                && this.figures[point.getX()][point.getY()] == null;
    }
}

