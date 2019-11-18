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
        int inner = innerIndex;
        boolean result = false;

        for (int outer = outerIndex; outer < values.length; outer++) {
            if (inner < values[outer].length) {
                outerIndex = outer;
                innerIndex = inner;
                result = true;
                break;
            } else {
                inner = 0;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }

        int result = values[outerIndex][innerIndex];

        if (innerIndex < values[outerIndex].length) {
            innerIndex++;
        } else {
            innerIndex = 0;
            outerIndex++;
        }

        return result;
    }

}
