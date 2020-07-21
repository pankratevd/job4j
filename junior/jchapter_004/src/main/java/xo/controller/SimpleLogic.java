package xo.controller;

import xo.controller.interfaces.IGameLogic;
import xo.model.Figure;
import xo.model.interfaces.IFrame;

import java.util.Arrays;
import java.util.Objects;

public class SimpleLogic implements IGameLogic {

    /**
     *
     * @param frame
     * @param figure
     * @return возвращает true, если фигура figure победила.
     */
    @Override
    public boolean isWin(IFrame frame, Figure figure) {
        return checkHorizon(frame, figure)
                || checkVertical(frame, figure)
                || checkDiagonal(frame, figure);
    }
    /**
     *
     * @param frame
     * @return возвращает true, если есть возможность выполнить следующий ход, false, если все поля заполнены
     */
    @Override
    public boolean isNextMoveAvailable(IFrame frame) {
       return Arrays.stream(frame.getFigures()).flatMap(Arrays::stream).anyMatch(Objects::isNull);
    }
    /**
     *
     * @param frame
     * @return возращает экземляр Figurе - фигура, осуществляющая ход.
     */
    @Override
    public Figure nextFigure(IFrame frame) {
        return Arrays.stream(frame.getFigures()).flatMap(Arrays::stream).filter(Objects::nonNull).count() % 2 == 0 ? Figure.X : Figure.O;
    }


    private boolean checkHorizon(IFrame frame, Figure figure) {
        boolean result = false;
        for (Figure[] line : frame.getFigures()) {
            int count = 0;
            for (Figure f : line) {
                if (f == figure) {
                    count++;
                }
                if (count == frame.getFigures().length) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean checkVertical(IFrame frame, Figure figure) {
        boolean result = false;
        for (int i = 0; i < frame.getFigures().length; i++) {
            int count = 0;
            for (int j = 0; j < frame.getFigures().length; j++) {
                if (frame.getFigures()[j][i] == figure) {
                    count++;
                }
                if (count == frame.getFigures().length) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean checkDiagonal(IFrame frame, Figure figure) {
        boolean result = false;
        int count = 0;
        for (int i = 0; i < frame.getFigures().length; i++) {
            if (frame.getFigures()[i][i] == figure) {
                count++;
            }
        }

        if (count == frame.getFigures().length) {
            result = true;
        } else {
            count = 0;
            for (int i = 0; i < frame.getFigures().length; i++) {
                if (frame.getFigures()[i][frame.getFigures().length - 1 - i] == figure) {
                    count++;
                }
            }
            if (count == frame.getFigures().length) {
                result = true;
            }
        }
        return result;
    }
}
