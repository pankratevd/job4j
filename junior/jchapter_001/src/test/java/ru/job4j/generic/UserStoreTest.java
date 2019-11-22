package ru.job4j.generic;

import org.junit.Test;

public class UserStoreTest {

    @Test
    public void whenAddRightElement() {
        UserStore userStore = new UserStore(5);

        userStore.add(new User("1"));
        userStore.add((new Role("2")));

        System.out.println(userStore.findById("1"));
        System.out.println(userStore.findById("2"));

    }
}
