package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
    @Test
    public void sumFromOneToTen() {
        Counter counter = new Counter();
        int result = counter.add(0, 10);
        assertThat(result, is(30));
    }

    @Test
    public void sumFromOneToOne() {
        Counter counter = new Counter();
        int result = counter.add(1, 1);
        assertThat(result, is(0));
    }

    @Test
    public void sumFromThreeToEleven() {
        Counter counter = new Counter();
        int result = counter.add(3, 11);
        assertThat(result, is(28));
    }
}
