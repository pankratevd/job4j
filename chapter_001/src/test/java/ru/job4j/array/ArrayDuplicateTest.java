package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
       ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
       String[] str = {"1", "2", "1", "2", "3", "3", "2"};
       String[] expected = {"1", "2", "3"};
       assertThat(arrayDuplicate.remove(str), is(expected));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate1() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] str = {"1", "1", "3", "2", "3", "3", "2", "1"};
        String[] expected = {"1", "3", "2"};
        assertThat(arrayDuplicate.remove(str), is(expected));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate2() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] str = {"1", "1", "1", "1", "1", "1", "1"};
        String[] expected = {"1"};
        assertThat(arrayDuplicate.remove(str), is(expected));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate3() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] str = {"1", "2", "3", "4", "5", "6", "7"};
        String[] expected = {"1", "2", "3", "4", "5", "6", "7"};
        assertThat(arrayDuplicate.remove(str), is(expected));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate4() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] str = {"1"};
        String[] expected = {"1"};
        assertThat(arrayDuplicate.remove(str), is(expected));
    }

}