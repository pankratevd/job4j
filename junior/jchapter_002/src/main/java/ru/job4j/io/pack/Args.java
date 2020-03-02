package ru.job4j.io.pack;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Args {

    private String[] arr;

    private File directory;

    private File output;

    private String exclude;

    Args(String[] arr) {
        this.arr = arr;
    }

    File directory() {
        return new File(arr[findByIndex("-d") + 1]);

    }

    List<String> exclude() {
        List<String> result = new ArrayList<>();

        int index = findByIndex("-e");
        index++;

        while (index < arr.length && !arr[index].startsWith("-")) {
            result.add(arr[index]);
            index++;
        }

        return result;
    }

    File output() {
        return new File(arr[findByIndex("-o") + 1]);
    }

    private int findByIndex(String s) {
        int result = -1;
        for (int i = 0; i < arr.length; i++) {
            if (s.equals(arr[i])) {
                System.out.println(arr[i]);
                result = i;
                break;
            }
        }
        return result;
    }
}

