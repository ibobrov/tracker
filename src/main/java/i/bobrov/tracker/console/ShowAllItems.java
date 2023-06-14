package i.bobrov.tracker.console;

import i.bobrov.tracker.Item;
import i.bobrov.tracker.Tracker;
import i.bobrov.tracker.io.Input;
import i.bobrov.tracker.io.Output;

public class ShowAllItems implements UserAction {
    private final Output out;

    public ShowAllItems(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Show all items ===");
        Item[] items = tracker.findAll();
        if (items.length > 0) {
            for (Item item : items) {
                out.println(item);
            }
        } else {
            out.println("Хранилище еще не содержит заявок.");
        }
        return true;
    }
}
