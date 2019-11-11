package ru.job4j.count;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CountTest {
    @Test
    public void checkCount() {
        List<Integer> list = new ArrayList<>();
        list.add(null);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(null);
        Integer expected = 56;
        Integer result = Count.count(list);
        assertThat(result, is(expected));
    }
}
