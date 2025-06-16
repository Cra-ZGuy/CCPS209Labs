package Lab7;

import java.util.LinkedList;
import java.util.List;

public class Tail extends FileProcessor<List<String>> {
    private final int n;
    private final LinkedList<String> buffer;

    public Tail(int n) {
        this.n = n;
        this.buffer = new LinkedList<>();
    }

    @Override
    protected void startFile() {
        buffer.clear();
    }

    @Override
    protected void processLine(String line) {
        if (buffer.size() == n) {
            buffer.removeFirst();
        }

        buffer.addLast(line);
    }

    @Override
    protected List<String> endFile() {
        return new LinkedList<>(buffer);
    }
}
