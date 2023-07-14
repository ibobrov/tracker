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

public class ReplaceActionTest {
    private final static String LN = System.lineSeparator();

    @Test
    public void whenReplaceHappiness() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askInt(any(String.class))).thenReturn(0);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        rep.execute(input, tracker);
        assertThat(out.toString()).isEqualTo("=== Edit item ===" + LN + "Заявка изменена успешно." + LN);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(replacedName);
    }

    @Test
    public void whenReplaceDontHappiness() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Not replaced item"));
        ReplaceAction rep = new ReplaceAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askInt(any(String.class))).thenReturn(5);
        rep.execute(input, tracker);
        assertThat(out.toString()).isEqualTo("=== Edit item ===" + LN + "Ошибка замены заявки." + LN);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo("Not replaced item");
    }
}