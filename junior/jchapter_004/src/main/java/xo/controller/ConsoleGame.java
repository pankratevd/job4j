package xo.controller;

import xo.controller.interfaces.IGame;
import xo.controller.interfaces.IGameLogic;
import xo.controller.interfaces.IInput;
import xo.model.Figure;
import xo.model.Frame;
import xo.model.Point;
import xo.model.interfaces.IFrame;
import xo.model.interfaces.IPoint;
import xo.view.ConsoleFiled;
import xo.view.interfaces.IField;

public class ConsoleGame implements IGame {

    public final static int DEFAULT_SIZE = 3;
    public final static Figure DEFAULT_FIGURE = Figure.O;

    private final Figure computerFigure;
    private IFrame frame;
    private IField field = new ConsoleFiled();
    private IInput input = new ConsoleInput();
    private IGameLogic logic = new SimpleLogic();


    public ConsoleGame(int size, Figure figure) {
        if (size < DEFAULT_SIZE) {
            size = 3;
        }
        this.frame = new Frame(size);
        this.computerFigure = figure;
    }

    @Override
    public void run() {
        while (true) {
            if (!logic.isNextMoveAvailable(frame)) {
                System.out.println("Игра закончена. Ничья.");
                break;
            }
            Figure currentFigure = logic.nextFigure(frame);

            if (logic.nextFigure(frame) == computerFigure) {
                IPoint point = new ComputerMove().getPoint(frame, logic, currentFigure);
                frame.setFigure(currentFigure, point);
                if (logic.isWin(frame, currentFigure)) {
                    System.out.println("Победитель: " + currentFigure);
                    field.draw(frame);
                    break;
                }
                continue;
            }

            field.draw(frame);
            Point point = input.inputCoordinate();
            while (!frame.accept(point)) {
                System.out.println("Недопустимая координата");
                point = input.inputCoordinate();
            }

            frame.setFigure(currentFigure, point);
            if (logic.isWin(frame, currentFigure)) {
                System.out.println("Победитель: " + currentFigure);
                field.draw(frame);
                break;
            }
        }
    }
}