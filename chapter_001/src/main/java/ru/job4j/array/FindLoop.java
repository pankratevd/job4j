package ru.job4j.array;

public class FindLoop {
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public int indexOf(int[] data, int el, int start, int finish) {
        int rst = -1;
        for (int index = start; index <= finish; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public int[] sort(int[] data) {
        int min = 0;
        //int indexMin=0;
        for (int i = 0; i < data.length - 1; i++) {
            min = data[i];
            for (int j = i + 1; j < data.length; j++) {
                if (min > data[j]) {
                    min = data[j];
                }
            }
            int indexMin = indexOf(data, min, i, data.length - 1);
            if (indexMin != i) {
                int tmp = data[i];
                data[i] = min;
                data[indexMin] = tmp;
            }
        }
        return data;
    }
}
