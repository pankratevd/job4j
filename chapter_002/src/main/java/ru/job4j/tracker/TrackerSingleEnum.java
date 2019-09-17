package ru.job4j.tracker;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public enum TrackerSingleEnum {
    INSTANCE;
    Tracker tracker = new Tracker();

    public Item add(Item item) {
       return tracker.add(item);
    }

    public boolean replace(String id, Item item) {
        return tracker.replace(id, item);
    }

    public boolean delete(String id) {
        return tracker.delete(id);
    }

    public Item[] findAll() {
        return tracker.findAll();
    }

    public Item[] findByName(String key) {
        return tracker.findByName(key);
    }

    public Item findById(String id) {
        return tracker.findById(id);
    }

/*    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }*/
}
