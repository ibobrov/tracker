package i.bobrov.tracker.console;

import i.bobrov.tracker.io.*;
import i.bobrov.tracker.store.SqlTracker;
import i.bobrov.tracker.store.Store;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    private void showMenu(UserAction[] actions) {
        out.println("Menu:");
        for (int index = 0; index < actions.length; index++) {
            out.println(index + ". " + actions[index].name());
        }
    }

    public void init(Input input, Store tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.length) {
                out.println("Wrong input, you can select: 0 .. " + (actions.length - 1));
                continue;
            }
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    public static void main(String[] args) throws Exception {
        try (Store tracker = new SqlTracker()) {
            Output out = new ConsoleOutput();
            Input input = new ValidateInput(out, new ConsoleInput());
            UserAction[] actions = {
                    new CreateAction(out),
                    new ShowAllItems(out),
                    new ReplaceAction(out),
                    new DeleteAction(out),
                    new FindById(out),
                    new FindByName(out),
                    new Exit()
            };
            new StartUI(out)
                    .init(input, tracker, actions);
        }
    }
}
