package ru.job4j.tracker;

import java.util.List;

public class TrackerSingleLazy {
    private static TrackerSingleLazy instance;
    Store store = new SqlTracker();

    public static TrackerSingleLazy getInstance() {
        if (instance == null) {
            instance = new TrackerSingleLazy();
        }
        return instance;
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
