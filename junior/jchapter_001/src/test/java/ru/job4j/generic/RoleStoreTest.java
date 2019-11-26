package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class RoleStoreTest {
    @Test
    public void whenFindById() {
        RoleStore roleStore = new RoleStore(5);

        Role r1 = new Role("1");
        Role r2 = new Role("2");
        Role r3 = new Role("3");
        Role r4 = new Role("4");

        roleStore.add(r1);
        roleStore.add(r2);
        roleStore.add(r3);
        roleStore.add(r4);

        assertThat(r3, is(roleStore.findById("3")));
        assertThat(r1, is(roleStore.findById("1")));
    }

    @Test
    public void whenAddElement() {
        RoleStore roleStore = new RoleStore(5);

        Role r1 = new Role("1");
        Role r2 = new Role("2");
        Role r3 = new Role("3");
        Role r4 = new Role("4");

        roleStore.add(r1);
        roleStore.add(r2);
        roleStore.add(r3);
        roleStore.add(r4);

        assertThat(r3, is(roleStore.findById("3")));
        assertThat(r1, is(roleStore.findById("1")));

    }

    @Test
    public void replaceRole() {
        RoleStore roleStore = new RoleStore(5);

        Role r1 = new Role("1");
        Role r2 = new Role("2");
        Role r3 = new Role("3");
        Role r4 = new Role("4");
        Role r5 = new Role("5");
        Role r6 = new Role("6");

        roleStore.add(r1);
        roleStore.add(r2);
        roleStore.add(r3);
        roleStore.add(r4);

        assertThat(r1, is(roleStore.findById("1")));
        assertThat(r3, is(roleStore.findById("3")));

        roleStore.replace("3", r5);
        roleStore.replace("1", r6);

        assertThat(r5, is(roleStore.findById("5")));
        assertThat(r6, is(roleStore.findById("6")));

        assertNull(roleStore.findById("1"));
        assertNull(roleStore.findById("3"));

    }

    @Test
    public void whenDelete() {

        RoleStore roleStore = new RoleStore(5);

        Role r1 = new Role("1");
        Role r2 = new Role("2");
        Role r3 = new Role("3");
        Role r4 = new Role("4");

        roleStore.add(r1);
        roleStore.add(r2);
        roleStore.add(r3);
        roleStore.add(r4);

        assertThat(r1, is(roleStore.findById("1")));
        assertThat(r3, is(roleStore.findById("3")));

        roleStore.delete("3");
        roleStore.delete("1");


        assertNull(roleStore.findById("1"));
        assertNull(roleStore.findById("3"));

    }
}
