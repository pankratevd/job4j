package pool;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {

    volatile AtomicInteger count = new AtomicInteger();
    volatile Set<Integer> set = new HashSet<>();
    @Ignore
    @Test
    public void test() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();


        for (int i = 0; i < 100; i++) {
            threadPool.work(new Thread(
                    () -> {
                        set.add(count.incrementAndGet());
                    }
            ));

        }
    }
}