package i.bobrov.tracker.console;

import i.bobrov.tracker.io.Input;
import i.bobrov.tracker.io.Output;
import i.bobrov.tracker.io.StubOutput;
import i.bobrov.tracker.model.Item;
import i.bobrov.tracker.store.MemTracker;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FindByNameTest {
    private final static String LN = System.lineSeparator();

    @Test
    public void whenFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("item");
        tracker.add(item);
        FindByName finder = new FindByName(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(item.getName());
        finder.execute(input, tracker);
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + LN + item + LN);
    }

    @Test
    public void whenFoundTwoItems() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("item");
        Item item2 = new Item("item");
        tracker.add(item1);
        tracker.add(item2);
        FindByName finder = new FindByName(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(item1.getName());
        finder.execute(input, tracker);
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + LN + item1 + LN + item2 + LN);
    }

    @Test
    public void whenNotFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("item");
        tracker.add(item);
        FindByName finder = new FindByName(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn("fail");
        finder.execute(input, tracker);
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + LN + "Заявки с именем: fail не найдены." + LN);
    }
}
