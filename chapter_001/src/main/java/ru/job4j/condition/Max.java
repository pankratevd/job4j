package ru.job4j.condition;

public class Max {
    public int max(int left, int right) {
        int result = left >= right ? left : right;
        return result;
    }

    public int max(int first, int second, int third) {
        int result = third >= max(first, second) ? third : max(first, second);
        return result;
    }

    public int max(int first, int second, int third, int fourth) {
        int result = fourth >= max(first, second, third) ? fourth : max(first, second, third);
        return result;
    }
}
