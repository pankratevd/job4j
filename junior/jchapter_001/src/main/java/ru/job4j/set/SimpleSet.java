package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable<E> {

    SimpleArrayList<E> list;

    public SimpleSet() {
        list = new SimpleArrayList<>();
    }

    public void add(E value) {
        if (!checkContained(value)) {
            list.add(value);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int itPosition = 0;
            @Override
            public boolean hasNext() {
                return itPosition < list.getSize();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return list.get(itPosition++);
            }
        };
    }

    private boolean checkContained(E value) {
        boolean result = false;
        for (int i = 0; i < list.getSize(); i++) {
            if (value.equals(list.get(i))) {
                result = true;
                break;
            }
        }
        return result;
    }
}
