package nonblocking;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {
    @Ignore
    @Test
    public void whenNoExceptionExpected() throws InterruptedException {
        for (int j = 0; j < 1000; j++) {

            CASCount casCount = new CASCount();
            int count = 100000;

            Thread th1 = new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    casCount.increment();
                }
            });
            Thread th2 = new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    casCount.increment();
                }
            });
            Thread th3 = new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    casCount.increment();
                }
            });

            th1.start();
            th2.start();
            th3.start();
            th1.join();
            th2.join();
            th3.join();

            assertThat(casCount.get(), is(3 * count));
        }
    }
}