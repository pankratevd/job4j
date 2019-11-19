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
        return outerIndex < values.length - 1 || innerIndex < values[outerIndex].length;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = values[outerIndex][innerIndex];

        if (innerIndex < values[outerIndex].length - 1) {
            innerIndex++;
        } else if
        (outerIndex < values.length - 1) {
            outerIndex++;
            innerIndex = 0;
        } else {
            innerIndex++;
        }

        return result;
    }
}
