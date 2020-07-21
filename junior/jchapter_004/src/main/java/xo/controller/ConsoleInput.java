package xo.controller;

import xo.controller.interfaces.IInput;
import xo.model.Point;

import java.util.Scanner;

public class ConsoleInput implements IInput {

    private Scanner sc = new Scanner(System.in);

    @Override
    public String askInput(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    @Override
    public Point inputCoordinate() {

        System.out.println("Введите координату x:");
        while (!sc.hasNextInt()) {
            System.out.println("Неверное значение. Введите координату x:");
            sc.nextLine();
        }
        int x = sc.nextInt();

        System.out.println("Введите координату Y:");
        while (!sc.hasNextInt()) {
            System.out.println("Неверное значение. Введите координату у:");
            sc.nextLine();
        }
        int y = sc.nextInt();

        return new Point(y, x);
    }
}
