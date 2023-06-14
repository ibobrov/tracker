package i.bobrov.tracker.console;

import i.bobrov.tracker.Tracker;
import i.bobrov.tracker.io.Input;

public interface UserAction {

    String name();

    boolean execute(Input input, Tracker tracker);
}
