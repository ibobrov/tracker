package i.bobrov.tracker.console;

import i.bobrov.tracker.io.Input;
import i.bobrov.tracker.store.Store;

public interface UserAction {

    String name();

    boolean execute(Input input, Store tracker);
}
