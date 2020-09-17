package concurrent.synch;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }

    @Test /* no exception expected */
    public void iterator() {
        SingleLockList<Integer> list = new SingleLockList<>();
        int count = 1000;

        for (int i = 0; i < count; i++) {
            Iterator<Integer> it = list.iterator();
            int finalI = i;
            new Thread(() -> list.add(finalI)).start();
            new Thread(() -> {
                while (it.hasNext()) {
                    it.next();
                }
            }).start();
        }
    }
}