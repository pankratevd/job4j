package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private T[] array;

    private int position = 0;

    public SimpleArray(final int size) {
        array = (T[]) new Object[size];
    }

    public void add(T model) {
        if (this.position >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        array[this.position++] = model;
    }

    public void set(int index, T model) {
        if (index >= this.position) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = model;
    }

    public void remove(int index) {
        if (index >= this.position) {
            throw new IndexOutOfBoundsException();
        }
        if (index == this.array.length - 1) {
            array[index] = null;
            this.position--;
        } else {
            System.arraycopy(this.array, index + 1, this.array, index, this.array.length - 1 - index);
            this.array[--this.position] = null;
        }
    }

    public T get(int index) {
        if (index >= this.position) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int itPosition = 0;
            boolean isNext = false;
            @Override
            public boolean hasNext() {
                return itPosition < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                isNext = true;
                return array[itPosition++];
            }
            @Override
            public void remove() {
                if (!isNext) {
                    throw new IllegalStateException();
                }
                isNext = false;
                SimpleArray.this.remove(itPosition);
            }
        };
    }
}
