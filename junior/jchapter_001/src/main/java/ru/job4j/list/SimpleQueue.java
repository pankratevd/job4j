package ru.job4j.list;

public class SimpleQueue<T> {

    SimpleStack<T> left;
    SimpleStack<T> right;

    public SimpleQueue() {
        left = new SimpleStack<T>();
        right = new SimpleStack<T>();
    }

    public T poll() {
        T result;

        if (right.empty()) {
            while (!left.empty()) {
                right.push(left.poll());
            }
        }
        result = right.poll();

        return result;
    }

    public void push(T value) {
        left.push(value);
    }
}
