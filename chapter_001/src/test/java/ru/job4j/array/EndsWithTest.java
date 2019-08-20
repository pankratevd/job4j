package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class EndsWithTest {

    @Test
    public void whenStartWithPrefixThenTrue() {
        EndsWith word = new EndsWith();
        boolean result = word.endsWith("Hello", "lo");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        EndsWith word = new EndsWith();
        boolean result = word.endsWith("Hello", "la");
        assertThat(result, is(false));
    }

    @Test
    public void whenStartWithPrefixThenTrue1() {
        EndsWith word = new EndsWith();
        boolean result = word.endsWith("Hello", "Hello");
        assertThat(result, is(true));
    }
}