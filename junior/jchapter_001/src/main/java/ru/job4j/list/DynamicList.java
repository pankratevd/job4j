package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;

    Object[] container;

    private int size = 0;

    private int modCount = 0;

    public DynamicList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    public void add(E value) {

        growContainer();

        container[size++] = value;

        modCount++;
    }

    public E get(int index) {

        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int itPosition = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                checkModify();
                return itPosition < size;
            }

            @Override
            public E next() {
                checkModify();

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[itPosition++];
            }

            private void checkModify() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    private Object[] growContainer() {
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length << 1);
        }
        return container;
    }

}
