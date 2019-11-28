package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DynamicListTest {

    private DynamicList<Integer> list;
    @Before
    public void beforeTest() {
        list = new DynamicList<>();
        for (int i = 0; i < 8; i++) {
            list.add(i);
        }
    }

    @Test
    public void whenAddAndLengthEnough() {
        list.add(9);
        list.add(10);
        assertThat(list.container[8], is(9));
        assertThat(list.container[9], is(10));
        assertThat(list.container.length, is(10));
    }

    @Test
    public void whenAddAndLengthNotEnough() {
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        assertThat(list.container.length, is(20));
        assertThat(list.container[8], is(9));
        assertThat(list.container[9], is(10));
        assertThat(list.container[10], is(11));
        assertThat(list.container[11], is(12));
    }
}
