package ru.job4j.calculator;

/**
 * Class Calculator для вычисления арифметических операций + / * -.
 * @author Dmitrii Pankratev (d.pankratiev@gmail.com)
 * @since 20.08.2019
 * @version 1
 */

public class Calculator {
    /**
     * Сложение
     * @param first
     * @param second
     * Выводит результат сложения в консоль.
     */
    public static void add(double first, double second) {
        double result = first + second;
        System.out.println(first + " + " + second + " = " + result);
    }

    /**
     * Деление
     * @param first
     * @param second
     * Выводит результат деления в консоль.
     */

    public static void div(double first, double second) {
        double result = first/second;
        System.out.println(first + " / " + second + " = " + result);
    }

    /**
     * Умножение
     * @param first
     * @param second
     * Выводит результат умножения в консоль.
     */

    public static void multiply (double first, double second) {
        double result = first*second;
        System.out.println(first + " * " + second + " = " + result);
    }

    /**
     * Вычитание
     * @param first
     * @param second
     * Выводит результат вычитания в консоль.
     */

    public static void subtrack(double first, double second) {
        double result = first-second;
        System.out.println(first + " - " + second + " = " + result);
    }

    /**
     * Main.
     * Вывод результата математических операций + / * - в консоль
     * @param args
     */
    public static void main(String[] args) {
        add(1,1);
        div(4,2);
        multiply(2,1);
        subtrack(10,5);
    }
}
