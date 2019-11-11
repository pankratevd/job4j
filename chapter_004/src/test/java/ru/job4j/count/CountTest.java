package ru.job4j.count;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CountTest {
    @Test
    public void checkCount() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7);
        Integer expected = 56;
        Integer result = Count.count(list);
        System.out.println(result);
        assertThat(result, is(expected));
    }
}
