package ru.job4j.list;

public class Node<T> {

    private T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    boolean hasCycle(Node<T> first) {
        Node slow = first;
        Node fast = first;
        boolean result = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }
}
