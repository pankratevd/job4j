package xo.model.interfaces;

import xo.model.Figure;

public interface IFrame {

    Figure[][] getFigures();

    void setFigure(Figure figure, IPoint point);

    boolean accept(IPoint point);
}
