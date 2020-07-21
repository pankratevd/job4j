package xo.controller;

import xo.controller.interfaces.IComputerMoveLogic;
import xo.controller.interfaces.IGameLogic;
import xo.model.*;
import xo.model.interfaces.IFrame;
import xo.model.interfaces.IPoint;

import java.util.Random;

public class ComputerMove implements IComputerMoveLogic {
    /**
     *
     * @param frame
     * @param logic
     * @param figure
     * @return возвращает Point - координаты свободной ячейки.
     */
    @Override
    public IPoint getPoint(IFrame frame, IGameLogic logic, Figure figure) {
        IPoint result = new Point(new Random().nextInt(9), new Random().nextInt(9));
        while (!frame.accept(result)) {
            result = new Point(new Random().nextInt(9), new Random().nextInt(9));
        }
        return result;
    }
}
