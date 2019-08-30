package ru.job4j.condition;

public class Max {
    public int max(int left, int right) {
        int result = left >= right ? left : right;
        return result;
    }

    public int max(int first, int second, int third) {
        return this.max(third, this.max(first, second));
    }

    public int max(int first, int second, int third, int fourth) {
        return this.max(fourth, this.max(first, second, third));
    }
}
