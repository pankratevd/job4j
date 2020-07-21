package xo;

import xo.controller.ConsoleGame;
import xo.controller.ConsoleInput;
import xo.controller.interfaces.IGame;
import xo.controller.interfaces.IInput;
import xo.model.Figure;

public class Start {
    public static int size = ConsoleGame.DEFAULT_SIZE;
    public static Figure figure = ConsoleGame.DEFAULT_FIGURE;

    public static void main(String[] args) {

        IInput input = new ConsoleInput();

        try {
            String in = input.askInput("Введите размер поля (по-умолчанию 3): ");
            if (!"".equals(in)) {
                size = Integer.parseInt(in);
            }
        } catch (NumberFormatException e) {
            System.out.println("Размер поля 3x3");
        }

        String inFigure = input.askInput("Фигура компьютера (X, O) (по-умолчанию - O)");
        if (!"".equals(inFigure)) {
            if ("X".equals(inFigure)) {
                figure = Figure.X;
            }
        }

        IGame game = new ConsoleGame(size, figure);
        game.run();
    }
}
