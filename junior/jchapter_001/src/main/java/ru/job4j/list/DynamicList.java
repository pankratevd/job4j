package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicList<E> implements Iterable<E> {
    private int DEFAULT_CAPACITY = 10;

    Object[] container;

    private int size = 0;

    private int modCount = 0;

    public DynamicList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    public void add(E value) {

        grow();

        container[size++] = value;
        modCount++;
    }

    public E get(int index) {
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            boolean isNext;
            int itPosition = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return false;
            }

            @Override
            public E next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                isNext = true;
                return (E) container[itPosition++];
            }
        };
    }

    private Object[] grow() {
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length << 1);
        }
        return container;
    }

}
