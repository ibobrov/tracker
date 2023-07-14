package i.bobrov.tracker.console;

import i.bobrov.tracker.io.*;

import i.bobrov.tracker.model.Item;
import i.bobrov.tracker.store.MemTracker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.assertj.core.api.Assertions.assertThat;

public class StartUITest {
    private MemTracker tracker = new MemTracker();
    private Output out = new StubOutput();
    private final StartUI ustartUI = new StartUI(out);
    private final String ln =  System.lineSeparator();

    @AfterEach
    public void cleanUp() {
        tracker = new MemTracker();
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
        assertThat(tracker.findAll().get(0).getName()).isEqualTo("Item name");
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
        UserAction[] actions = {
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Exit Program" + ln
        );
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        UserAction[] actions = new UserAction[]{
                new ReplaceAction(out),
                new Exit()
        };
        ustartUI.init(in, tracker, actions);
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
        );
    }

    @Test
    public void whenShowAllItemsTestOutputIsSuccessfully() {
        Item one = tracker.add(new Item("test1"));
        Item two = tracker.add(new Item("test2"));
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        UserAction[] actions = new UserAction[]{
                new ShowAllItems(out),
                new Exit()
        };
        ustartUI.init(in, tracker, actions);
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Show all items ===" + ln
                        + one + ln
                        + two + ln
                        + "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
        );
    }

    @Test
    public void whenFindByIdTestOutputIsSuccessfully() {
        tracker.add(new Item("test1"));
        Item two = tracker.add(new Item("test2"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(two.getId()), "1"}
        );
        UserAction[] actions = new UserAction[]{
                new FindById(out),
                new Exit()
        };
        ustartUI.init(in, tracker, actions);
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Find item by id ===" + ln
                        + two + ln
                        + "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
        );
    }

    @Test
    public void whenFindByNameTestOutputIsSuccessfully() {
        tracker.add(new Item("test1"));
        Item two = tracker.add(new Item("test2"));
        Item three = tracker.add(new Item("test2"));
        Input in = new StubInput(
                new String[] {"0", two.getName(), "1"}
        );
        UserAction[] actions = new UserAction[]{
                new FindByName(out),
                new Exit()
        };
        ustartUI.init(in, tracker, actions);
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Find items by name ===" + ln
                        + two + ln
                        + three + ln
                        + "Menu:" + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
        );
    }

    @Test
    public void whenInvalidExit() {
        Input in = new StubInput(
                new String[] {"1", "0"}
        );

        UserAction[] actions = new UserAction[]{
                new Exit()
        };
        ustartUI.init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Exit Program" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu:" + ln
                        + "0. Exit Program" + ln
        );
    }
}
