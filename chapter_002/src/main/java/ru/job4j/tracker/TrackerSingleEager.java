package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class TrackerSingleEager {
    private static final TrackerSingleEager INSTANCE = new TrackerSingleEager();
    Store store = new SqlTracker();

    private TrackerSingleEager() {
    }

    public static TrackerSingleEager getInstance() {
        return INSTANCE;
    }

    public Item add(Item item) {
        return store.add(item);
    }

    public boolean replace(String id, Item item) {
        return store.replace(id, item);
    }

    public boolean delete(String id) {
        return store.delete(id);
    }

    public List<Item> findAll() {
        return store.findAll();
    }

    public List<Item> findByName(String key) {
        return store.findByName(key);
    }

    public Item findById(String id) {
        return store.findById(id);
    }

}
