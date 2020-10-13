package ru.job4j.generic;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test
    public void whenAddThreeStringElementsThenSecondElementIsRight() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("string1");
        array.add("string2");
        array.add("string3");
        String expected = "string2";
        assertThat(array.get(1), is(expected));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddMoreElementsThenIndexThenException() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("string1");
        array.add("string2");
        array.add("string3");
        array.add("string4");
    }

    @Test
    public void getElementWhenExist() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("string1");
        array.add("string2");
        array.add("string3");
        String expected = "string2";
        assertThat(array.get(1), is(expected));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getElementWhenNotExistThenException() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("string1");
        String expected = "string1";
        assertThat(array.get(0), is(expected));
        array.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveNotExistThenException() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.remove(0);

    }

    @Test
    public void whenSetSecondElementThenSecondIsChange() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("string1");
        array.add("string2");
        array.add("string3");
        String expectedBefore = "string2";

        assertThat(array.get(1), is(expectedBefore));

        array.set(1, "stringChanged");
        String expectedAfter = "stringChanged";

        assertThat(array.get(1), is(expectedAfter));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetOutOfIndexThenException() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("string0");
        array.set(1, "string");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveSecondElementInMiddle() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(0);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.remove(2);
        Integer expected0 = 0;
        Integer expected1 = 1;
        Integer expected2 = 3;
        Integer expected3 = 4;
        Integer expected4 = null;
        assertThat(array.get(0), is(expected0));
        assertThat(array.get(1), is(expected1));
        assertThat(array.get(2), is(expected2));
        assertThat(array.get(3), is(expected3));
        assertThat(array.get(4), is(expected4));

        array.add(5);
        assertThat(array.get(4), is(5));

        array.add(6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemovedIndexMoreThenExistThenException() {
        SimpleArray<Object> array = new SimpleArray<>(3);

        array.add(new Object());
        array.add(new Object());
        array.remove(3);

    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveTheLastElement() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(0);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);

        array.remove(4);

        Integer expected0 = 0;
        Integer expected1 = 1;
        Integer expected2 = 2;
        Integer expected3 = 3;
        Integer expected4 = null;
        assertThat(array.get(0), is(expected0));
        assertThat(array.get(1), is(expected1));
        assertThat(array.get(2), is(expected2));
        assertThat(array.get(3), is(expected3));
        assertThat(array.get(4), is(expected4));

        array.add(5);
        assertThat(array.get(4), is(5));

        array.add(6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveTheFirstElement() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(0);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);

        array.remove(0);

        Integer expected0 = 1;
        Integer expected1 = 2;
        Integer expected2 = 3;
        Integer expected3 = 4;
        Integer expected4 = null;
        assertThat(array.get(0), is(expected0));
        assertThat(array.get(1), is(expected1));
        assertThat(array.get(2), is(expected2));
        assertThat(array.get(3), is(expected3));
        assertThat(array.get(4), is(expected4));

        array.add(5);
        assertThat(array.get(4), is(5));

        array.add(6);
    }

    @Test(expected = NoSuchElementException.class)
    public void testsIterateThroughCollection() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("string1");
        array.add("string2");
        array.add("string3");
        Iterator<String> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("string1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("string2"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("string3"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void testRemoveIteratorWhenPreviousIsNext() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("string1");
        array.add("string2");
        array.add("string3");

        Iterator<String> it = array.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("string1"));
        it.remove();
        assertThat(it.next(), is("string3"));
        assertThat(it.hasNext(), is(false));
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void testRemoveIteratorWhenDoubleRemoveThenException() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("string1");
        array.add("string2");
        array.add("string3");

        Iterator<String> it = array.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("string1"));
        it.remove();
        it.remove();
    }


}
