package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class FactorialTest {
    @Test
    public void factorialOfFive() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        assertThat(result,is(120));
    }

    @Test
    public void factorialOfZero() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        assertThat(result,is(1));
    }
    }
