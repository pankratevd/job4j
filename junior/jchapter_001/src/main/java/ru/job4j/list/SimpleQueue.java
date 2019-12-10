package ru.job4j.list;

public class SimpleQueue<T> {

    SimpleStack<T> stack;

    public SimpleQueue() {
        stack = new SimpleStack<T>();
    }

    public T poll() {
        T result;
        SimpleStack<T> temp = new SimpleStack<>();
        T value;
        value = stack.poll();
        while (value != null) {
            temp.push(value);
            value = stack.poll();
        }
        result = temp.poll();
        value = temp.poll();
        while (value != null) {
            stack.push(value);
            value = temp.poll();
        }
        return result;
    }

    public void push(T value) {
        stack.push(value);
    }
}
