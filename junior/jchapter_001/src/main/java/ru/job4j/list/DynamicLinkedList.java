package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicLinkedList<E> implements Iterable<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> first;
    private Node<E> last;

    public void add(E value) {
        if (size == 0) {
            Node<E> node = new Node<>(null, value, null);
            first = node;
            last = node;
            size++;
            modCount++;
        } else {
            Node<E> oldLast = last;
            Node<E> node = new Node<>(first, value, null);
            last = node;
            oldLast.next = last;
            last.prev = oldLast;
            size++;
            modCount++;
        }
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> result = first;
        if (index > 0) {
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        }

        return result.item;
    }

    public void removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            last = null;
            first = null;
            modCount++;
            size--;
        } else {
            last.prev.next = null;
            last = last.prev;
            modCount++;
            size--;
        }
    }

    public E poll() {
        E result;
        if (size == 0) {
            result = null;
        } else {
            result = first.item;
            Node<E> oldFirst = first;
            first = first.next;
            oldFirst.next = null;
            modCount++;
            size--;
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int modCountExpected = modCount;
            Node<E> it = first;

            @Override
            public boolean hasNext() {
                checkModify();
                return it != null;
            }

            @Override
            public E next() {
                checkModify();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Node<E> result = it;

                if (result != first) {
                    result = it;
                    it = it.next;
                } else {
                    it = it.next;
                }

                return result.item;
            }

            private void checkModify() {
                if (modCountExpected != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };

    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
