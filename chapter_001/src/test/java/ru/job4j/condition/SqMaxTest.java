package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SqMaxTest {

    @Test
    public void whenFirstMax() {
        int result = SqMax.max(6,5,4,3);
        assertThat(result,is(6));
    }

    @Test
    public void whenSecondMax() {
        int result = SqMax.max(6,8,4,3);
        assertThat(result,is(8));
    }

    @Test
    public void whenThirdMax() {
        int result = SqMax.max(6,5,9,3);
        assertThat(result,is(9));
    }

    @Test
    public void whenFourthMax() {
        int result = SqMax.max(6,5,4,8);
        assertThat(result,is(8));
    }

    @Test
    public void FirstSecondEqualsMax() {
        int result = SqMax.max(5,5,4,4);
        assertThat(result,is(5));
    }
}
