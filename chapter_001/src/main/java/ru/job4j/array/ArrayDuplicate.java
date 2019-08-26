package ru.job4j.array;

import java.sql.Array;
import java.util.Arrays;

public class ArrayDuplicate {
    public String[] remove(String[] array) {
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == null) {
                continue;
            }
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                array[j] = null;
                count++;
                }
            }
        }
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == null) {
                for (int k = i; k < array.length - 1; k++) {
                    array[k] = array[k + 1];
                    array[k + 1] = null;
                }
            }
        }
        return Arrays.copyOf(array, array.length - count);
    }


}
