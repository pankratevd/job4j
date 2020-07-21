package xo.controller.interfaces;

import xo.model.Figure;
import xo.model.interfaces.IFrame;
import xo.model.interfaces.IPoint;

/**
 * Интерфейс для получения координаты точки при ходе компьютера.
 */
public interface IComputerMoveLogic {

    IPoint getPoint(IFrame frame, IGameLogic logic, Figure figure);

}
