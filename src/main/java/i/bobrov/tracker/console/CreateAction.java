package i.bobrov.tracker.console;

import i.bobrov.tracker.model.Item;
import i.bobrov.tracker.io.Input;
import i.bobrov.tracker.io.Output;
import i.bobrov.tracker.store.Store;

public class CreateAction implements UserAction {
    private final Output out;

    public CreateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new Item";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Create a new Item ===");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        store.add(item);
        out.println("Добавленная заявка: " + item);
        return true;
    }
}
