package ru.job4j.sort;

import java.util.Arrays;

public class Merge {
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int count = 0;
        int indexL = 0;
        int indexR = 0;
        while (indexL < left.length && indexR < right.length) {
            if (left[indexL] < right[indexR]) {
                rsl[count] = left[indexL];
                count++;
                indexL++;
            } else {
                rsl[count] = right[indexR];
                count++;
                indexR++;
            }
        }

        for (int i = indexL; i < left.length; i++) {
            rsl[count] = left[i];
            count++;
        }

        for (int i = indexR; i < right.length; i++) {
            rsl[count] = right[i];
            count++;
        }
        return rsl;
    }

    public static void main(String[] args) {
        Merge process = new Merge();
        int[] rsl = process.merge(
                new int[]{1, 3, 5},
                new int[]{2, 4}
        );
        System.out.println(Arrays.toString(rsl));
    }
}
