package ru.job4j.array;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {

    @Test
    public void whenArrayHas5Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = 0;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHasnt5() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {6, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHas7Then3() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3, 7};
        int value = 7;
        int result = find.indexOf(input, value);
        int expect = 3;
        assertThat(result, is(expect));
    }
    @Test
    public void whenArrayHas7Then3StrartIndex2EndIndex3() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3, 7};
        int value = 7;
        int result = find.indexOf(input, value, 2, 3);
        int expect = 3;
        assertThat(result, is(expect));
    }
    @Test
    public void whenArrayHas7ThenNoneStartIndex0EndIndex2() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3, 7};
        int value = 7;
        int result = find.indexOf(input, value, 0, 2);
        int expect = -1;
        assertThat(result, is(expect));
    }

}