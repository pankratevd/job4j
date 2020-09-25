package nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        if (count.get() == null) {
            count.compareAndSet(null, 0);
        }
    }

    public void increment() {
        Integer ref;
        do {
            ref = count.get();
        } while (!count.compareAndSet(ref, ++ref));
    }

    public int get() {
        return count.get();
    }
}
