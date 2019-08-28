package ru.job4j.condition;

import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenMax1To2Then2() {
        Max max = new Max();
        int result = max.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenMax3To3Then3() {
        Max max = new Max();
        int result = max.max(3, 3);
        assertThat(result, is(3));
    }

    @Test
    public void whenMax5To3Then5() {
        Max max = new Max();
        int result = max.max(5, 3);
        assertThat(result, is(5));
    }

    @Test
    public void when1to2to3Then3() {
        Max max = new Max();
        int result = max.max(1, 2, 3);
        assertThat(result, is(3));
    }

    @Test
    public void when3to2to1Then3() {
        Max max = new Max();
        int result = max.max(3, 2, 1);
        assertThat(result, is(3));
    }

    @Test
    public void when1to2to3to4Then4() {
        Max max = new Max();
        int result = max.max(4, 1, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void when3to4to2to1Then4() {
        Max max = new Max();
        int result = max.max(3, 4, 2, 1);
        assertThat(result, is(4));
    }

    @Test
    public void when4to4to4to1Then4() {
        Max max = new Max();
        int result = max.max(4, 4, 4, 1);
        assertThat(result, is(4));
    }
}
