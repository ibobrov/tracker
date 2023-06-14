package i.bobrov.tracker.console;

import i.bobrov.tracker.*;
import i.bobrov.tracker.io.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.assertj.core.api.Assertions.assertThat;

public class StartUITest {
    private Tracker tracker = new Tracker();
    private Output out = new StubOutput();
    private final StartUI ustartUI = new StartUI(out);

    @AfterEach
    public void cleanUp() {
        tracker = new Tracker();
        out = new StubOutput();
    }

    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        UserAction[] actions = {
                new CreateAction(out),
                new Exit()
        };
        ustartUI.init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName()).isEqualTo("Item name");
    }

    @Test
    public void whenReplaceItem() {
        Item item = tracker.add(new Item( "Replaced item")); /* Добавляется в tracker новая заявка */
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0",
                        String.valueOf(item.getId()),
                        replacedName,
                        "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(out),
                new Exit()
        };
        ustartUI.init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    public void whenDeleteItem() {
        Item item = tracker.add(new Item("Deleted item")); /* Добавляется в tracker новая заявка */
        Input in = new StubInput(
                new String[] {"0",
                        String.valueOf(item.getId()),
                        "1"}
        );
        UserAction[] actions = {
                new DeleteAction(out),
                new Exit()
        };
        ustartUI.init(in, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenExit() {
        Input in = new StubInput(
                new String[] {"0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString()).isEqualTo(
                "Menu." + System.lineSeparator()
                        + "0. Exit Program" + System.lineSeparator()
        );
    }
}
