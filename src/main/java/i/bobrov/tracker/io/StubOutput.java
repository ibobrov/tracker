package i.bobrov.tracker.io;

public class StubOutput implements Output {
    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void print(Object obj) {
        if (obj != null) {
            buffer.append(obj);
        } else {
            buffer.append("null");
        }
    }

    @Override
    public void println(Object obj) {
        print(obj);
        buffer.append(System.lineSeparator());
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
