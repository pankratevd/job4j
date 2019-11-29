package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void addElementToSetWhenNotContained() {
        SimpleSet<String> set = new SimpleSet<>();

        set.add("1");
        set.add("2");
        set.add("3");

        assertThat(set.list.getSize(), is(3));
        assertThat(set.list.get(0), is("3"));
        assertThat(set.list.get(1), is("2"));
        assertThat(set.list.get(2), is("1"));
    }

    @Test
    public void addElementWhenExistThenNotAdded() {
        SimpleSet<Integer> set = new SimpleSet<>();

        set.add(1);
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
        set.add(2);
        set.add(3);

        assertThat(set.list.getSize(), is(3));
        assertThat(set.list.get(0), is(3));
        assertThat(set.list.get(1), is(2));
        assertThat(set.list.get(2), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorWithoutChangeContainer() {

        SimpleSet<Integer> set = new SimpleSet<>();
        Iterator<Integer> it = set.iterator();

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
        set.add(2);
        set.add(3);

        assertTrue(it.hasNext());
        assertThat(it.next(), is(3));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertFalse(it.hasNext());

        it.next();
    }
}
