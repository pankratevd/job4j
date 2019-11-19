package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MatrixIteratorTest {

    @Test
    public void whenHasNextReturnTrue() {
        MatrixIterator it = new MatrixIterator(new int[][]{{1, 2}, {3, 4}});

        boolean result = it.hasNext();
        boolean expected = true;

        assertThat(result, is(expected));
    }

    @Test
    public void whenFirstEmptyAndHasNextReturnTrue() {
        MatrixIterator it = new MatrixIterator(new int[][]{{}, {2}});

        boolean result = it.hasNext();
        boolean expected = true;

        assertThat(result, is(expected));
    }

    @Test
    public void whenHasNotNextReturnFalse() {
        MatrixIterator it = new MatrixIterator(new int[][]{{1}, {3, 4}});

        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        boolean result = it.hasNext();
        boolean expected = false;

        assertThat(result, is(expected));
    }

    @Test
    public void whenHasNextOnEmptyArrayReturnFalse() {
        MatrixIterator it = new MatrixIterator(new int[][]{{}});

        boolean result = it.hasNext();
        boolean expected = false;

        assertThat(result, is(expected));
    }

    @Test
    public void whenEmptyInMiddleElement() {
        MatrixIterator it = new MatrixIterator(new int[][]{{1, 2}, {6, 4, 5}, {3}});
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));


    }

    @Test
    public void whenEmptyInAll() {
        MatrixIterator it = new MatrixIterator(new int[][]{{2}});
        Integer result = it.next();
        Integer expected = 2;

        assertThat(result, is(expected));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptySurround() {
        MatrixIterator it = new MatrixIterator(new int[][] {{1, 2}, {3, 4, 5}});
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
        it.next();
    }


    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenException() {
        MatrixIterator it = new MatrixIterator(new int[][]{{}});
        it.next();

    }


}