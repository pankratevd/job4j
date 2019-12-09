package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DynamicLinkedListTest {


    private DynamicLinkedList<Integer> list;

    Iterator<Integer> it;

    @Before
    public void beforeTest() {
        list = new DynamicLinkedList<Integer>();


        for (int i = 0; i < 8; i++) {
            list.add(i);
        }

        it = list.iterator();
    }

    @Test
    public void checkGetWhenExist() {
        assertThat(list.getSize(), is(8));
        assertThat(list.get(0), is(0));
        assertThat(list.get(1), is(1));
        assertThat(list.get(2), is(2));
        assertThat(list.get(3), is(3));
        assertThat(list.get(4), is(4));
        assertThat(list.get(5), is(5));
        assertThat(list.get(6), is(6));
        assertThat(list.get(7), is(7));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkWhenListContainsOneElement() {
        list = new DynamicLinkedList<>();
        list.add(0);
        assertThat(list.getSize(), is(1));
        assertThat(list.get(0), is(0));
        list.get(1);
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

    @Test (expected = NoSuchElementException.class)
    public void whenRemoveAllItemsOnFillListThenException() {
        assertThat(list.getSize(), is(8));
        assertThat(list.get(7), is(7));
        list.removeLast();
        assertThat(list.getSize(), is(7));
        assertThat(list.get(6), is(6));
        list.removeLast();
        assertThat(list.getSize(), is(6));
        assertThat(list.get(5), is(5));
        list.removeLast();
        assertThat(list.getSize(), is(5));
        assertThat(list.get(4), is(4));
        list.removeLast();
        assertThat(list.getSize(), is(4));
        assertThat(list.get(3), is(3));
        list.removeLast();
        assertThat(list.getSize(), is(3));
        assertThat(list.get(2), is(2));
        list.removeLast();
        list.removeLast();
        assertThat(list.getSize(), is(1));
        assertThat(list.get(0), is(0));
        list.removeLast();
        assertThat(list.getSize(), is(0));
        list.removeLast();
    }

    @Test
    public void consistentlyPoll() {
        assertThat(list.poll(), is(0));
        assertThat(list.poll(), is(1));
        assertThat(list.poll(), is(2));
        assertThat(list.poll(), is(3));
        assertThat(list.poll(), is(4));
        assertThat(list.poll(), is(5));
        assertThat(list.poll(), is(6));
        assertThat(list.poll(), is(7));
        assertNull(list.poll());

    }
}
