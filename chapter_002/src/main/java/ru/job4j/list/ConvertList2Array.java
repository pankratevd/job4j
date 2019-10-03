package ru.job4j.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {

        int cells = list.size() / rows;
        if (list.size() % rows != 0) {
            cells++;
        }

        int index = 0;
        int count = 0;
        int[][] array = new int[rows][cells];
        for (int[] internal : array) {
            index = 0;
            for (int y : internal) {
                if (count < list.size()) {
                    internal[index++] = list.get(count++);
                } else {
                    break;
                }
            }
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int i : array) {
                result.add(i);
            }
        }
        return result;
    }
}

