package i.bobrov.tracker.console;

import i.bobrov.tracker.Tracker;
import i.bobrov.tracker.io.Input;
import i.bobrov.tracker.io.Output;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("=== Delete item ==="
                + System.lineSeparator() + "Enter id: ");
        if (tracker.delete(id)) {
            out.println("Заявка удалена успешно.");
        } else {
            out.println("Ошибка удаления заявки.");
        }
        return true;
    }
}
