package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
        boolean startLeft = data[0][0];
        boolean startRight = data[0][data.length - 1];
        for (int i = 0; i < data.length; i++) {
            if ((data[i][i]) != startLeft || data[i][data.length - 1 - i] != startRight) {
                result = false;
            }
        }
        return result;
    }

}
