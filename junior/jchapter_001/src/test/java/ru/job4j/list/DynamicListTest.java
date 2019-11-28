package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DynamicListTest {

    private DynamicList<Integer> list;

    Iterator<Integer> it;

    @Before
    public void beforeTest() {
        list = new DynamicList<>();


        for (int i = 0; i < 8; i++) {
            list.add(i);
        }

        it = list.iterator();
    }

    @Test
    public void whenAddAndLengthEnough() {
        list.add(8);
        list.add(9);
        assertThat(list.container[8], is(8));
        assertThat(list.container[9], is(9));
        assertThat(list.container.length, is(10));
    }

    @Test
    public void whenAddAndLengthNotEnough() {
        list.add(8);
        list.add(9);
        assertThat(list.container.length, is(10));
        list.add(10);
        assertThat(list.container.length, is(20));
        list.add(11);
        assertThat(list.container[8], is(8));
        assertThat(list.container[9], is(9));
        assertThat(list.container[10], is(10));
        assertThat(list.container[11], is(11));
    }

    @Test
    public void whenGetIndexExistIndexThenReturn() {
        assertThat(list.get(0), is(0));
        assertThat(list.get(5), is(5));
        assertThat(list.get(7), is(7));

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetIndexLessZeroThenException() {
        list.get(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetIndexMoreSizeThenException() {
        list.get(8);
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorWithoutChangeContainer() {
        assertTrue(it.hasNext());
        assertThat(it.next(), is(0));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(3));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(4));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(5));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(6));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(7));
        assertFalse(it.hasNext());
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModifiedAndIteratorHasNextThenException() {
        assertTrue(it.hasNext());
        list.add(8);
        it.hasNext();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModifiedAndIteratorNextThenException() {
        assertTrue(it.hasNext());
        list.add(8);
        it.next();
    }
}
