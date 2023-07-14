package i.bobrov.tracker.store;

import i.bobrov.tracker.model.Item;

import java.util.List;

public final class SingleTracker {
    private static SingleTracker instance = null;
    private static final MemTracker TRACKER = new MemTracker();

    private SingleTracker() {
    }

    public static SingleTracker getInstance() {
        if (instance == null) {
            instance = new SingleTracker();
        }
        return instance;
    }

    public Item add(Item item) {
        return TRACKER.add(item);
    }

    public List<Item> findAll() {
        return TRACKER.findAll();
    }

    public List<Item> findByName(String key) {
        return TRACKER.findByName(key);
    }

    public Item findById(int id) {
        return TRACKER.findById(id);
    }

    public boolean replace(int id, Item item) {
        return TRACKER.replace(id, item);
    }

    public boolean delete(int id) {
        return TRACKER.delete(id);
    }
}
