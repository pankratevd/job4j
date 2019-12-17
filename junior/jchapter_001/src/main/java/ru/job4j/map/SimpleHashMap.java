package ru.job4j.map;

import java.util.HashSet;
import java.util.Set;

public class SimpleHashMap<K, V> {

    final private static int DEFAULT_CAPACITY = 8;

    final private static double LOAD_FACTORY = 0.75;

    private double threshold = DEFAULT_CAPACITY * LOAD_FACTORY;

    private int size = 0;

    private Entry<K, V>[] array;

    public SimpleHashMap() {
        array = new Entry[DEFAULT_CAPACITY];
    }

    public boolean insert(K key, V value) {
        boolean result = true;
        Entry<K, V> node = new Entry<>(key, value);

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

    public V get(K key) {
        V result = null;
        int i = index(key);
        if (array[i] != null) {
            result = array[i].value;
        }
        return result;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> result = new HashSet<>();
        for (Entry<K, V> e : array) {
            if (e != null) {
                result.add(e);
            }
        }
        return result;
    }

    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        for (Entry<K, V> e : array) {
            if (e != null) {
                result.add(e.key);
            }
        }
        return result;
    }

    int getArraySize() {
        return array.length;
    }

    private int index(K key) {
        int result;

        if (key != null) {
            result = key.hashCode() % (array.length - 1);
        } else {
            result = 0;
        }
        return result;
    }

    private void resize() {
        Entry<K, V>[] oldArray = array;
        int capacity = oldArray.length * 2;
        array = new Entry[capacity];
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null) {
                array[index(oldArray[i].key)] = oldArray[i];
            }
        }
        threshold = capacity * LOAD_FACTORY;
    }


    public static class Entry<K, V> {

        private K key;

        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
