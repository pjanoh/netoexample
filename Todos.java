import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Todos implements Iterable<String> {
    private List<String> primary = new ArrayList<>();
    private List<String> secondary = new ArrayList<>();

    public Todos addPrimary(String task) {
        primary.add(task);
        return this;
    }

    public Todos addSecondary(String task) {
        secondary.add(task);
        return this;
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            boolean isPrimary = true;
            int nextNum = 0;

            @Override
            public boolean hasNext() {
                if (isPrimary) {
                    if (nextNum < primary.size()) {
                        return true;
                    } else {
                        return !secondary.isEmpty();
                    }
                } else {
                    return nextNum < secondary.size();
                }
            }

            @Override
            public String next() {
                if (isPrimary) {
                    if (nextNum < primary.size()) {
                        String task = primary.get(nextNum);
                        nextNum++;
                        return task;
                    } else {
                        isPrimary = false;
                        String task = secondary.get(0);
                        nextNum = 1;
                        return task;
                    }
                } else {
                    String task = secondary.get(nextNum);
                    nextNum++;
                    return task;
                }
            }
        };
    }
}
