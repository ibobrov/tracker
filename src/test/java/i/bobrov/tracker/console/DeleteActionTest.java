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

public class DeleteActionTest {
    private final static String LN = System.lineSeparator();

    @Test
    public void whenDeleteHappiness() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Deleted item"));
        DeleteAction del = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askInt(any(String.class))).thenReturn(0);
        del.execute(input, tracker);
        assertThat(out.toString()).isEqualTo("=== Delete item ===" + LN + "Заявка удалена успешно." + LN);
        assertThat(tracker.findAll().isEmpty()).isTrue();
    }

    @Test
    public void whenDeleteDontHappiness() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("Not deleted item");
        tracker.add(item);
        DeleteAction del = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        del.execute(input, tracker);
        assertThat(out.toString()).isEqualTo("=== Delete item ===" + LN + "Ошибка удаления заявки." + LN);
        assertThat(tracker.findAll().size()).isEqualTo(1);
        assertThat(tracker.findAll().get(0)).isEqualTo(item);
    }
}
