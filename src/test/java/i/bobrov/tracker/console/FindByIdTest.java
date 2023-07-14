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

public class FindByIdTest {
    private final static String LN = System.lineSeparator();

    @Test
    public void whenFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("item");
        tracker.add(item);
        FindById finder = new FindById(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askInt(any(String.class))).thenReturn(0);
        finder.execute(input, tracker);
        assertThat(out.toString()).isEqualTo("=== Find item by id ===" + LN + item + LN);
    }

    @Test
    public void whenNotFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("item");
        tracker.add(item);
        FindById finder = new FindById(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        finder.execute(input, tracker);
        assertThat(out.toString()).isEqualTo("=== Find item by id ===" + LN + "Заявка с введенным id: 1 не найдена." + LN);
    }
}
