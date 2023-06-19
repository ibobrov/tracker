package i.bobrov.tracker;

import java.util.List;
import java.util.ArrayList;

public class Tracker {
    private final List<Item> items = new ArrayList<>();

    public Item add(Item item) {
        if (item != null) {
            int id = items.size();
            item.setId(id);
            items.add(id, item);
        }
        return item;
    }

    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();
        for (Item i : items) {
            if (i != null && key.equals(i.getName())) {
                rsl.add(i);
            }
        }
        return rsl;
    }

    public Item findById(int id) {
        return id >= 0 && id < items.size() ? items.get(id) : null;
    }

    public boolean replace(int id, Item item) {
        boolean rsl = item != null && deleteAndCorrectIds(id, false);
        if (rsl) {
            item.setId(id);
            items.add(id, item);
        }
        return rsl;
    }

    public boolean delete(int id) {
        return deleteAndCorrectIds(id, true);
    }

    private boolean deleteAndCorrectIds(int id, boolean isCorrect) {
        boolean rsl = findById(id) != null && items.remove(id) != null;
        if (isCorrect && rsl) {
            for (int i = id; i < items.size(); i++) {
                items.get(i).setId(i);
            }
        }
        return rsl;
    }
}