package wait;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {
    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(10);
        int count  = 1000;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < count; i++) {
            set.add(i);
        }
        Thread producer = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                sbq.offer(i);
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                set.remove(sbq.poll());

            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        Assert.assertThat(set.size(), is(0));
    }

}