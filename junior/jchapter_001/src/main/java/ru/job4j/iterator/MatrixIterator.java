package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {

    private int[][] values;

    private int innerIndex = 0;

    private int outerIndex = 0;


    public MatrixIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        int currentInnerIndex = innerIndex;
        boolean result = false;

        for (int i = outerIndex; i < values.length; i++) {
            if (values[i].length > currentInnerIndex) {
                result = true;
                break;
            } else {
                currentInnerIndex = 0;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = 0;
        for (int i = outerIndex; i < values.length; i++) {
            if (values[i].length > innerIndex) {
                result = values[i][innerIndex++];
                break;
            } else {
                innerIndex = 0;
                outerIndex = i + 1;
            }
        }
        return result;
    }

}
