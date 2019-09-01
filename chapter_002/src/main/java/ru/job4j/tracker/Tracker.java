package ru.job4j.tracker;

import java.util.Random;

public class Tracker {
    private Item[] items = new Item[100];
    private int position = 0;

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    public boolean replace(String id, Item item) {
        boolean result = false;
        Item replaceable = this.findById(id);
        if (replaceable != null) {
            for (int i = 0; i < position; i++) {
                if (this.items[i].equals(replaceable)) {
                    this.items[i] = item;
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        int index = 0;
        Item item = this.findById(id);
        if (item != null) {
            for (int i = 0; i < position; i++) {
                if (this.items[i].equals(item)) {
                    index = i;
                }
            }
            if (index == items.length - 1) {
                items[items.length - 1] = null;
                position--;
                result = true;
            } else {
                System.arraycopy(this.items, index + 1, this.items, index, this.items.length - 1 - index);
                items[position - 1] = null;
                position--;
                result = true;
            }
        }
        return result;
    }

    public Item[] findAll() {
        Item[] array = new Item[position];
        System.arraycopy(items, 0, array, 0, position);
        return array;
    }

    public Item[] findByName(String key) {
        Item[] temp = new Item[position];
        int k = 0;
        for (int i = 0; i < position; i++) {
            if (key.equals(this.items[i].getName())) {
                temp[k] = this.items[i];
                k++;
            }
        }
        Item[] array = new Item[k];
        System.arraycopy(temp, 0, array, 0, k);
        return array;
    }

    public Item findById(String id) {
        Item result = null;
        for (int i = 0; i < position; i++) {
            if (id.equals(this.items[i].getId())) {
                result = this.items[i];
                break;
            }
        }
        return result;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }
}
