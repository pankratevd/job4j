package concurrent.store;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void add() {
        UserStore store = new UserStore();
        User u1 = new User(1, 100);
        User u2 = new User(2, 100);
        assertTrue(store.add(u1));
        assertTrue(store.add(u2));
        assertFalse(store.add(u1));
    }

    @Test
    public void update() {
        UserStore store = new UserStore();
        User u1 = new User(1, 100);
        User u2 = new User(2, 100);
        User u1updated = new User(1, 200);
        store.add(u1);
        assertFalse(store.update(u2));
        assertTrue(store.update(u1updated));
    }

    @Test
    public void delete() {
        UserStore store = new UserStore();
        User u1 = new User(1, 100);
        User u2 = new User(2, 100);
        store.add(u1);
        assertFalse(store.delete(u2));
        assertTrue(store.delete(u1));
    }

    @Test
    public void transfer() throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        int count = 10000;
        User u1 = new User(1, count);
        User u2 = new User(2, count);
        UserStore userStore = new UserStore();
        userStore.add(u1);
        userStore.add(u2);


        int i = 0;
        int k = 0;

        while (i < count) {
            list.add(i++, new Thread(() -> userStore.transfer(1, 2, 1)));
            list.add(i++, new Thread(() -> userStore.transfer(2, 1, 1)));
            list.get(k++).start();
            list.get(k++).start();
        }

        for (Thread t : list) {
            t.join();
        }
        assertThat(u1.getAmount(), is(count));
        assertThat(u2.getAmount(), is(count));

    }
}