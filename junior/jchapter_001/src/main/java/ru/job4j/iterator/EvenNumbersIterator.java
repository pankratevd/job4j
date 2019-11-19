package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class EvenNumbersIterator implements Iterator {

    private final int[] values;

    private int index = 0;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer action) {

    }

    @Override
    public boolean hasNext() {

        boolean result = false;

        for (int i = index; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                index = i;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }

        return values[index++];
    }
}
