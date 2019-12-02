package ru.job4j.list;

public class SimpleStack<T> {

    DynamicLinkedList<T> stack;

    public SimpleStack() {
        stack = new DynamicLinkedList<>();
    }

    public T poll() {
        T result;
        int size = stack.getSize();
        if (stack.getSize() == 0) {
            result = null;
        } else {
            result = (T) stack.get(size - 1);
            stack.removeLast();
        }
        return result;
    }

    public void push(T value) {
        stack.add(value);
    }

}
