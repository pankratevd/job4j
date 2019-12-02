package ru.job4j.set;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> list;

    public SimpleSet() {
        list = new SimpleArray<>(5);
    }

    public void add(E value) {
        if (!checkContained(value)) {
            list.add(value);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    private boolean checkContained(E value) {
        boolean result = false;
        for (E item : list) {
            if (value.equals(item)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
