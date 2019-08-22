package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean result = true;
        boolean value = data[0];
        for (int i = 0; i < data.length; i++) {
        if (data[i] != value) {
            result = false;
            break;
        }
        }
        return result;
    }
}
