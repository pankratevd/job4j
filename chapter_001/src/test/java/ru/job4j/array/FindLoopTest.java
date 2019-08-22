package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {

    @Test
    public void whenArrayHas5Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = 0;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHasnt5() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{6, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHas7Then3() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{5, 10, 3, 7};
        int value = 7;
        int result = find.indexOf(input, value);
        int expect = 3;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHas7Then3StrartIndex2EndIndex3() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{5, 10, 3, 7};
        int value = 7;
        int result = find.indexOf(input, value, 2, 3);
        int expect = 3;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHas7ThenNoneStartIndex0EndIndex2() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{5, 10, 3, 7};
        int value = 7;
        int result = find.indexOf(input, value, 0, 2);
        int expect = -1;
        assertThat(result, is(expect));
    }

    @Test
    public void whenSort5() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{3, 4, 1, 2, 5};
        int[] result = find.sort(input);
        int[] expect = new int[]{1, 2, 3, 4, 5};
        assertThat(result, is(expect));
    }

    @Test
    public void whenSort6() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{3, 1, 1, 1, 1};
        int[] result = find.sort(input);
        int[] expect = new int[]{1, 1, 1, 1, 3};
        assertThat(result, is(expect));
    }

    @Test
    public void whenSort7() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{1, 1, 1, 1, 1, 2};
        int[] result = find.sort(input);
        int[] expect = new int[]{1, 1, 1, 1, 1, 2};
        assertThat(result, is(expect));
    }

    @Test
    public void whenSort8() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{8, 7, 6, 5, 4, 3, 2, 1};
        int[] result = find.sort(input);
        int[] expect = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        assertThat(result, is(expect));
    }

    @Test
    public void whenSort9() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] result = find.sort(input);
        int[] expect = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(result, is(expect));
    }

}