package ru.job4j.list;

public class SimpleQueue<T> {

    SimpleStack<T> stack;

    public SimpleQueue() {
        stack = new SimpleStack<T>();
    }

    public T poll() {
        return stack.pollFirst();
    }

    public void push(T value) {
        stack.push(value);
    }
}
