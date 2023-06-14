package i.bobrov.tracker.console;

import i.bobrov.tracker.Tracker;
import i.bobrov.tracker.io.Input;

public class Exit implements UserAction {

    @Override
    public String name() {
        return "Exit Program";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        return false;
    }
}
