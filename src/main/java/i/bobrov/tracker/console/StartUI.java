package i.bobrov.tracker.console;

import i.bobrov.tracker.io.ConsoleOutput;
import i.bobrov.tracker.io.Input;
import i.bobrov.tracker.io.ConsoleInput;
import i.bobrov.tracker.Tracker;
import i.bobrov.tracker.io.Output;

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
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        Output out = new ConsoleOutput();
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
