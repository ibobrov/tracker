package i.bobrov.tracker.console;

import i.bobrov.tracker.Item;
import i.bobrov.tracker.Tracker;
import i.bobrov.tracker.io.Input;
import i.bobrov.tracker.io.Output;

import java.util.Objects;

public class FindById implements UserAction {
    private final Output out;

    public FindById(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find item by id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Find item by id ===");
        int id = input.askInt("Enter id: ");
        Item item = tracker.findById(id);
        out.println(Objects.requireNonNullElseGet(
                item,
                () -> "Заявка с введенным id: " + id + " не найдена."));
        return true;
    }
}
