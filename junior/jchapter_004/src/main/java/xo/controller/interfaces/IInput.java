package xo.controller.interfaces;

import xo.model.Point;

public interface IInput {

    String askInput(String message);

    Point inputCoordinate();

}
