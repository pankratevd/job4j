package xo.controller;

import org.junit.Test;
import xo.controller.interfaces.IGameLogic;
import xo.model.Figure;
import xo.model.Frame;
import xo.model.Point;
import xo.model.interfaces.IFrame;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleLogicTest {

    @Test
    public void isWinTest() {
        IFrame frame = new Frame(3);
        Figure figure = Figure.X;
        IGameLogic logic = new SimpleLogic();

        assertFalse(logic.isWin(frame, figure));

        frame.setFigure(figure, new Point(0, 0));
        frame.setFigure(figure, new Point(1, 1));
        frame.setFigure(figure, new Point(2, 2));

        assertFalse(logic.isWin(frame, Figure.O));
        assertTrue(logic.isWin(frame, figure));
    }

    @Test
    public void isNextMoveAvailable() {
        IFrame frame = new Frame(3);
        IGameLogic logic = new SimpleLogic();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 2 && j == 2) {
                    break;
                }
                frame.setFigure(Figure.X, new Point(i, j));
                assertTrue(logic.isNextMoveAvailable(frame));
            }
        }
        frame.setFigure(Figure.X, new Point(2, 2));
        assertFalse(logic.isNextMoveAvailable(frame));
    }

    @Test
    public void nextFigure() {
        IFrame frame = new Frame(3);
        IGameLogic logic = new SimpleLogic();
        assertThat(logic.nextFigure(frame), is(Figure.X));
        frame.setFigure(Figure.X, new Point(0, 0));
        assertThat(logic.nextFigure(frame), is(Figure.O));
    }

    @Test
    public void whenFigureFillsLineThenTrue() {

        Frame board = new Frame(3);
        board.setFigure(Figure.X, new Point(0, 0));
        board.setFigure(Figure.X, new Point(0, 1));
        board.setFigure(Figure.X, new Point(0, 2));
        SimpleLogic logic = new SimpleLogic();
        assertTrue(logic.isWin(board, Figure.X));

        board = new Frame(3);
        logic = new SimpleLogic();
        board.setFigure(Figure.O, new Point(1, 0));
        board.setFigure(Figure.O, new Point(1, 1));
        board.setFigure(Figure.O, new Point(1, 2));
        assertTrue(logic.isWin(board, Figure.O));

        board = new Frame(3);
        logic = new SimpleLogic();
        board.setFigure(Figure.X, new Point(2, 0));
        board.setFigure(Figure.X, new Point(2, 1));
        board.setFigure(Figure.X, new Point(2, 2));
        assertTrue(logic.isWin(board, Figure.X));

        board = new Frame(3);
        logic = new SimpleLogic();
        board.setFigure(Figure.X, new Point(0, 0));
        board.setFigure(Figure.X, new Point(1, 0));
        board.setFigure(Figure.X, new Point(2, 0));
        assertTrue(logic.isWin(board, Figure.X));

        board = new Frame(3);
        logic = new SimpleLogic();
        board.setFigure(Figure.X, new Point(0, 1));
        board.setFigure(Figure.X, new Point(1, 1));
        board.setFigure(Figure.X, new Point(2, 1));
        assertTrue(logic.isWin(board, Figure.X));

        board = new Frame(3);
        logic = new SimpleLogic();
        board.setFigure(Figure.O, new Point(0, 2));
        board.setFigure(Figure.O, new Point(1, 2));
        board.setFigure(Figure.O, new Point(2, 2));
        assertTrue(logic.isWin(board, Figure.O));


    }

    @Test
    public void whenFigureDoesntFillLineThenFalse() {

        Frame board = new Frame(3);
        board.setFigure(Figure.X, new Point(0, 0));
        board.setFigure(Figure.X, new Point(0, 1));
        board.setFigure(Figure.O, new Point(0, 2));
        SimpleLogic logic = new SimpleLogic();
        assertFalse(logic.isWin(board, Figure.X));

        board = new Frame(3);
        logic = new SimpleLogic();
        board.setFigure(Figure.O, new Point(1, 0));
        board.setFigure(Figure.O, new Point(1, 1));
        board.setFigure(Figure.X, new Point(1, 2));
        assertFalse(logic.isWin(board, Figure.O));

        board = new Frame(3);
        logic = new SimpleLogic();
        board.setFigure(Figure.X, new Point(2, 0));
        board.setFigure(Figure.O, new Point(2, 1));
        board.setFigure(Figure.X, new Point(2, 2));
        assertFalse(logic.isWin(board, Figure.X));

        board = new Frame(3);
        logic = new SimpleLogic();
        board.setFigure(Figure.O, new Point(0, 0));
        board.setFigure(Figure.O, new Point(1, 0));
        board.setFigure(Figure.X, new Point(2, 0));
        assertFalse(logic.isWin(board, Figure.O));

        board = new Frame(3);
        logic = new SimpleLogic();
        board.setFigure(Figure.X, new Point(0, 1));
        board.setFigure(Figure.O, new Point(1, 1));
        board.setFigure(Figure.X, new Point(2, 1));
        assertFalse(logic.isWin(board, Figure.X));

        board = new Frame(3);
        logic = new SimpleLogic();
        board.setFigure(Figure.X, new Point(0, 2));
        board.setFigure(Figure.O, new Point(1, 2));
        board.setFigure(Figure.X, new Point(2, 2));
        assertFalse(logic.isWin(board, Figure.X));

    }

    @Test
    public void whenFigureFillsDiagonalThenTrue() {
        Frame board = new Frame(3);
        board.setFigure(Figure.O, new Point(0, 0));
        board.setFigure(Figure.O, new Point(1, 1));
        board.setFigure(Figure.O, new Point(2, 2));
        SimpleLogic logic = new SimpleLogic();
        assertTrue(logic.isWin(board, Figure.O));

        board = new Frame(3);
        board.setFigure(Figure.X, new Point(0, 0));
        board.setFigure(Figure.X, new Point(1, 1));
        board.setFigure(Figure.X, new Point(2, 2));
        logic = new SimpleLogic();
        assertTrue(logic.isWin(board, Figure.X));

        board = new Frame(3);
        board.setFigure(Figure.X, new Point(2, 0));
        board.setFigure(Figure.X, new Point(1, 1));
        board.setFigure(Figure.X, new Point(0, 2));
        logic = new SimpleLogic();
        assertTrue(logic.isWin(board, Figure.X));

        board = new Frame(3);
        board.setFigure(Figure.O, new Point(0, 0));
        board.setFigure(Figure.O, new Point(1, 1));
        board.setFigure(Figure.O, new Point(2, 2));
        logic = new SimpleLogic();
        assertTrue(logic.isWin(board, Figure.O));

    }

    @Test
    public void whenFigureDoesntFillsDiagonalThenFalse() {
        Frame board = new Frame(3);
        board.setFigure(Figure.X, new Point(0, 0));
        board.setFigure(Figure.O, new Point(1, 1));
        board.setFigure(Figure.O, new Point(2, 2));
        SimpleLogic logic = new SimpleLogic();
        assertFalse(logic.isWin(board, Figure.O));


        board = new Frame(3);
        board.setFigure(Figure.X, new Point(2, 0));
        board.setFigure(Figure.O, new Point(1, 1));
        board.setFigure(Figure.X, new Point(0, 2));
        logic = new SimpleLogic();
        assertFalse(logic.isWin(board, Figure.X));

        board = new Frame(3);
        board.setFigure(Figure.O, new Point(0, 0));
        board.setFigure(Figure.O, new Point(1, 1));
        board.setFigure(Figure.X, new Point(2, 2));
        logic = new SimpleLogic();
        assertFalse(logic.isWin(board, Figure.O));

    }
}