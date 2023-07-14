package i.bobrov.tracker.console;

import i.bobrov.tracker.store.MemTracker;
import i.bobrov.tracker.io.Input;
import i.bobrov.tracker.store.Store;

public class Exit implements UserAction {

    @Override
    public String name() {
        return "Exit Program";
    }

    @Override
    public boolean execute(Input input, Store store) {
        return false;
    }
}
