package i.bobrov.tracker.console;

import i.bobrov.tracker.io.*;
import i.bobrov.tracker.Tracker;

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

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
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
