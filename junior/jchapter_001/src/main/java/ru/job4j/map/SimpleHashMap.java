package ru.job4j.map;

import java.util.Objects;

public class SimpleHashMap<K, V> {

    final private static int DEFAULT_CAPACITY = 8;

    final private static double LOAD_FACTORY = 0.75;

    private double threshold = DEFAULT_CAPACITY * LOAD_FACTORY;

    private int size = 0;

    Node<K, V>[] array;

    public SimpleHashMap() {
        array = new Node[DEFAULT_CAPACITY];
    }

    public boolean insert(K key, V value) {
        boolean result = true;
        Node<K, V> node = new Node<>(key, value);

        int i = index(key);
        if (array[i] != null) {
            result = false;
        } else {
            array[i] = node;
            size++;
        }
        if (size > threshold) {
            resize();
        }
        return result;
    }

    public boolean delete(K key) {
        boolean result = false;
        int i = index(key);
        if (array[i] != null) {
            array[i] = null;
            result = true;
            size--;
        }
        return result;
    }

    private int index(K key) {
        return Objects.hashCode(key) % array.length;
    }

    public V get(K key) {
        V result = null;
        int i = index(key);
        if (array[i] != null) {
            result = array[i].value;
        }
        return result;
    }

    int getArraySize() {
        return array.length;
    }

    private void resize() {
        Node<K, V>[] oldArray = array;
        int capacity = oldArray.length * 2;
        array = new Node[capacity];
        threshold = capacity * LOAD_FACTORY;
    }


    public static class Node<K, V> {

        K key;

        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
