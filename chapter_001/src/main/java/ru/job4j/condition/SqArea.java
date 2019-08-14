package ru.job4j.condition;

public class SqArea {
    public static int square(int p, int k) {
        return (int)(k*Math.pow(p/(2*(k+1)),2));
    }

    public static void main(String[] args) {
        int result1 = square(4, 1);
        System.out.println(" p = 4, k = 1, s = 1, real = " + result1);

        int result2 = square(6, 2);
        System.out.println(" p = 6, k = 2, , s = 2, real = " + result2);
    }
}
