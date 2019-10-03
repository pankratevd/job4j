package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Random;

public class Tracker {
    private ArrayList<Item> items = new ArrayList<>();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    public boolean replace(String id, Item item) {
        boolean result = false;
        int index = this.items.indexOf(this.findById(id));
        if (index != -1) {
            item.setId(id);
            items.set(index, item);
            result = true;
        }
        return result;
    }

    public boolean delete(String id) {
        return items.remove(this.findById(id));
    }

    public ArrayList<Item> findAll() {
        return this.items;
    }

    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> arrayList = new ArrayList<>();
        for (Item item : items) {
            if (key.equals(item.getName())) {
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (id.equals(item.getId())) {
                result = item;
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
