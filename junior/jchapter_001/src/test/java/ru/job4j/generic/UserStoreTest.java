package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class UserStoreTest {

    @Test
    public void whenFindById() {
        UserStore userStore = new UserStore(5);

        User u1 = new User("1");
        User u2 = new User("2");
        User u3 = new User("3");
        User u4 = new User("4");

        userStore.add(u1);
        userStore.add(u2);
        userStore.add(u3);
        userStore.add(u4);

        assertThat(u3, is(userStore.findById("3")));
        assertThat(u1, is(userStore.findById("1")));
    }

    @Test
    public void whenAddElement() {
        UserStore userStore = new UserStore(5);

        User u1 = new User("1");
        User u2 = new User("2");
        User u3 = new User("3");
        User u4 = new User("4");

        userStore.add(u1);
        userStore.add(u2);
        userStore.add(u3);
        userStore.add(u4);

        assertThat(u3, is(userStore.findById("3")));
        assertThat(u1, is(userStore.findById("1")));

    }

    @Test
    public void replaceUser() {
        UserStore userStore = new UserStore(5);

        User u1 = new User("1");
        User u2 = new User("2");
        User u3 = new User("3");
        User u4 = new User("4");
        User u5 = new User("5");
        User u6 = new User("6");

        userStore.add(u1);
        userStore.add(u2);
        userStore.add(u3);
        userStore.add(u4);

        assertThat(u1, is(userStore.findById("1")));
        assertThat(u3, is(userStore.findById("3")));

        userStore.replace("3", u5);
        userStore.replace("1", u6);

        assertThat(u5, is(userStore.findById("5")));
        assertThat(u6, is(userStore.findById("6")));

        assertNull(userStore.findById("1"));
        assertNull(userStore.findById("3"));

    }

    @Test
    public void whenDelete() {

        UserStore userStore = new UserStore(5);

        User u1 = new User("1");
        User u2 = new User("2");
        User u3 = new User("3");
        User u4 = new User("4");

        userStore.add(u1);
        userStore.add(u2);
        userStore.add(u3);
        userStore.add(u4);

        assertThat(u1, is(userStore.findById("1")));
        assertThat(u3, is(userStore.findById("3")));

        userStore.delete("3");
        userStore.delete("1");


        assertNull(userStore.findById("1"));
        assertNull(userStore.findById("3"));

    }
}
