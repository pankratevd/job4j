package ru.job4j.generic;

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
        int i = -1;
        for (T t : array) {
            i++;
            if (id.equals(t.getId())) {
                index = i;
                break;
            }
        }
        return index;
    }

}
