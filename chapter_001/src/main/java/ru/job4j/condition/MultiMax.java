package ru.job4j.condition;

public class MultiMax {
    public int max(int first, int second, int third) {
        int result = first;
        int maxOfTwo = first >= second ? first : second;
        result = maxOfTwo >= third ? maxOfTwo : third;
        return result;
    }
}
