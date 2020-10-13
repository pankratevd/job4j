package cache;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

public class ModelCacheTest {
    @Test
    public void whenOptimisticException() throws InterruptedException {
        ModelCache modelCache = new ModelCache();
        AtomicReference<Exception> ex = new AtomicReference<>();
        Base b1 = new Base(1, "task_1");
        Base b2 = new Base(2, "task_2");
        Base b3 = new Base(3, "task_3");

        modelCache.add(b1);
        modelCache.add(b2);
        modelCache.add(b3);

        Thread th1 = new Thread(() -> {
            try {
                for (int i = 1; i < 10; i++) {
                    Base newModel = new Base(1, "1");
                    newModel.setVersion(i);
                    modelCache.update(newModel);
                }
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });
        Thread th2 = new Thread(() -> {
            try {
                for (int i = 1; i < 10; i++) {
                    Base newModel = new Base(1, "1");
                    newModel.setVersion(i);
                    modelCache.update(newModel);
                }
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });

        th1.start();
        th2.start();
        th1.join();
        th2.join();
        System.out.println("Exception: " + ex.get().getMessage());
        assertNotNull(ex.get());
    }

    @Test
    public void whenUpdateInOneThreadThenOK() {
        ModelCache modelCache = new ModelCache();
        Base b1 = new Base(1, "task_1");
        Base b2 = new Base(2, "task_2");
        Base b3 = new Base(3, "task_3");

        modelCache.add(b1);
        modelCache.add(b2);
        modelCache.add(b3);

        modelCache.update(b1);
        modelCache.update(b1);
        assertThat(modelCache.getModel(1).getVersion(), Matchers.is(2));
    }
}