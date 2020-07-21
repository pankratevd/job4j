package xo.controller.interfaces;

import xo.model.Figure;
import xo.model.interfaces.IFrame;

/**
 * Интерфейс описания логики игрового процесса
 */

public interface IGameLogic {


    boolean isWin(IFrame frame, Figure figure);

    boolean isNextMoveAvailable(IFrame frame);

    Figure nextFigure(IFrame frame);

}
