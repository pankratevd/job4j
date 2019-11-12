package ru.job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {

        return checkHorizontal(Figure3T::hasMarkX) || checkVertical(Figure3T::hasMarkX) || checkDiagonal(Figure3T::hasMarkX);

    }

    public boolean isWinnerO() {

        return checkHorizontal(Figure3T::hasMarkO) || checkVertical(Figure3T::hasMarkO) || checkDiagonal(Figure3T::hasMarkO);

    }

    public boolean hasGap() {

        return Arrays.stream(this.table).flatMap(Arrays::stream).map(f -> f.hasMarkO() || f.hasMarkX()).anyMatch(x -> x.equals(false));

    }

    private boolean checkHorizontal(Predicate<Figure3T> predicate) {
        return Arrays.stream(this.table).map(x -> Arrays.stream(x).map(predicate::test).allMatch(b -> b.equals(true))).anyMatch(x -> x.equals(true));
    }


    private boolean checkVerticalLine(Predicate<Figure3T> predicate, int column) {
        boolean result = true;
        for (int i = 0; i < this.table.length; i++) {
            Figure3T cell = this.table[i][column];
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean checkVertical(Predicate<Figure3T> predicate) {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            if (checkVerticalLine(predicate, i)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean checkDiagonal(Predicate<Figure3T> predicate) {
        boolean result = true;
        for (int i = 0; i < this.table.length; i++) {
            Figure3T cell = this.table[i][i];
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }

        if (!result) {
            result = true;
            for (int i = 0; i < this.table.length; i++) {
                Figure3T cell = this.table[this.table.length - 1 - i][i];
                if (!predicate.test(cell)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}
