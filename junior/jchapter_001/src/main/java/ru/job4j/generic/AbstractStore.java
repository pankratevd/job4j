package ru.job4j.generic;

import java.util.Iterator;

abstract class AbstractStore<T extends Base> implements Store<T> {

    SimpleArray<T> array;

    AbstractStore(int size) {
        this.array = new SimpleArray(size);
    }

    @Override
    public void add(T model) {
        array.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = findIndex(id);
        if (index != -1) {
            array.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = findIndex(id);
        if (index != -1) {
        array.remove(index);
        result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T model : array) {
            if (id.equals(model.getId())) {
                result = model;
                break;
            }
        }
        return result;
    }

    private int findIndex(String id) {
        int index = -1;
        int i = 0;
        Iterator<T> it = array.iterator();
        while (it.hasNext()) {
            i++;
            if (id.equals(it.next().getId())) {
                index = i;
                break;
            }
        }
        return index;
    }
}
