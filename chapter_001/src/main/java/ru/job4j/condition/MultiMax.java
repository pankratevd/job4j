package ru.job4j.condition;

public class MultiMax {
    public int max(int first, int second, int third) {
        int result = first;
        int max = first >= second ? first : second;
        result = max >= third ? max : third;
        return result;
    }
}
